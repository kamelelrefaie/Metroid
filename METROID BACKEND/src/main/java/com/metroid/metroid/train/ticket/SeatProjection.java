package com.metroid.metroid.train.ticket;

import javax.persistence.*;

public interface SeatProjection  {

     int getId() ;

     int getSeatNumber() ;

     Ticket getTicket();
}
