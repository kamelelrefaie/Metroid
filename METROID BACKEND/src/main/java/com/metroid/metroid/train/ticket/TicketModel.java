package com.metroid.metroid.train.ticket;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class TicketModel
{
    private String tClass;
    private int price;
    private LocalDate date;
    private List<Seat> seats;
    private int quantity;

}
