package org.example.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.bookmyshow.models.User;

import java.util.List;

@Getter
@Setter
public class BookMovieRequestDTO {
    private List<Long> showSeatIds;
    private Long showId;
    private Long userId;
}
