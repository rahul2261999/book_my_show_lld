package org.example.bookmyshow.service;

import org.example.bookmyshow.Repository.ShowSeatTypeRepository;
import org.example.bookmyshow.models.Show;
import org.example.bookmyshow.models.ShowSeat;
import org.example.bookmyshow.models.ShowSeatType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceCalculatorService {
  private ShowSeatTypeRepository showSeatTypeRepository;
  PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
    this.showSeatTypeRepository = showSeatTypeRepository;
  }


  public Integer calculatePrice(List<ShowSeat> showSeats, Show show) {
    List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

    Map<String, Integer> typeAndAmountMap = new HashMap<String, Integer>();

    for (ShowSeatType showSeatType: showSeatTypes) {
      typeAndAmountMap.put(showSeatType.getSeatType().getName(), showSeatType.getPrice());
    }

    Integer amount = 0;

    for (ShowSeatType showSeatType: showSeatTypes) {
      amount += typeAndAmountMap.get(showSeatType.getSeatType().getName());
    }

    return amount;
  }
}
