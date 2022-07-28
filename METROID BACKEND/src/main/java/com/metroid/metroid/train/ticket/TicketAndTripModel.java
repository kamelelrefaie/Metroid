package com.metroid.metroid.train.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


public interface TicketAndTripModel {
     long getId() ;
     LocalDateTime getArrTime() ;
     LocalDateTime getDeptTime() ;
     LocalDate getDate() ;
     int getPrice() ;
}
