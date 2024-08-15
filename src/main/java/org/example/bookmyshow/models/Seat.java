package org.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private  String seatNumber;
    private  int rowNo;
    private  int colNo;
    @ManyToOne
    private  SeatType seatType;
}
