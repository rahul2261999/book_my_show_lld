package org.example.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDTO {
    private ResponseStatus responseStatus;
    private int amount;
    private Long bookingId;

}
