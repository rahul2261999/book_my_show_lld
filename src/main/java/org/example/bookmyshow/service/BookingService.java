package org.example.bookmyshow.service;

import org.example.bookmyshow.Repository.BookingRepository;
import org.example.bookmyshow.Repository.ShowRepository;
import org.example.bookmyshow.Repository.ShowSeatRepository;
import org.example.bookmyshow.Repository.UserRepository;
import org.example.bookmyshow.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
  private UserRepository userRepository;

  private ShowRepository showRepository;
  private ShowSeatRepository showSeatRepository;
  private BookingRepository bookingRepository;

  private PriceCalculatorService priceCalculatorService;

  @Autowired
  BookingService(
    UserRepository userRepository,
    ShowRepository showRepository,
    ShowSeatRepository showSeatRepository,
    BookingRepository bookingRepository,
    PriceCalculatorService priceCalculatorService
    ) {
    this.userRepository = userRepository;
    this.showRepository = showRepository;
    this.showSeatRepository = showSeatRepository;
    this.bookingRepository = bookingRepository;
    this.priceCalculatorService = priceCalculatorService;
  }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookTicket(
      List<Long> showSeatIds,
      Long showId,
      Long userId
    ) {
      Optional<User> userOptional = userRepository.findById(userId);

      if(userOptional.isEmpty()) {
        throw new RuntimeException("User not found");
      }

      User user = userOptional.get();

      Optional<Show> optionalShow = showRepository.findById(showId);

      if(optionalShow.isEmpty()) {
        throw new RuntimeException("Show not found");
      }

      Show show = optionalShow.get();

      List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

      for (ShowSeat showSeat: showSeats) {
        if (!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.EMPTY)) ||
                (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                        Duration.between(showSeat.getBlockedAt().toInstant(), new Date().toInstant()).toMinutes() < 15)
        ) {
          throw new RuntimeException("Not all selected seats are available");
        }
      }

      List<ShowSeat> savedShowSeats = new ArrayList<>();
      for (ShowSeat showSeat: showSeats) {
        showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
        showSeat.setBlockedAt(new Date());

        savedShowSeats.add(showSeatRepository.save(showSeat));
      }

      Booking booking = new Booking();

      booking.setUser(user);
      booking.setShow(show);
      booking.setShowSeats(showSeats);
      booking.setBookingStatus(BookingStatus.PENDING);
      booking.setBookedAt(new Date());

      Integer price = priceCalculatorService.calculatePrice(
              showSeats,
              show
      );

      booking.setAmount(price);
      booking.setPayments(new ArrayList<>());

      return bookingRepository.save(booking);
    }
}
