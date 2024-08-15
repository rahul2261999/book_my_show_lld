package org.example.bookmyshow.controllers;

import org.example.bookmyshow.dtos.BookMovieRequestDTO;
import org.example.bookmyshow.dtos.BookMovieResponseDTO;
import org.example.bookmyshow.dtos.ResponseStatus;
import org.example.bookmyshow.models.Booking;
import org.example.bookmyshow.service.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;


    BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDTO bookTicket(BookMovieRequestDTO body) {
        BookMovieResponseDTO bookMovieResponseDTO = new BookMovieResponseDTO();

        try {
            Booking booking = bookingService.bookTicket(
                    body.getShowSeatIds(),
                    body.getShowId(),
                    body.getUserId()
            );

            bookMovieResponseDTO.setBookingId(booking.getId());
            bookMovieResponseDTO.setAmount(booking.getAmount());
        } catch (Exception ex) {
            bookMovieResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }

        return  bookMovieResponseDTO;
    }
}
 