package com.metroid.metroid.train.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketGetInfoResponse {
    private long tripId;
    private LocalDate confirmDate;
    private int price;
    private String firstName;
    private LocalDateTime deptTime;
    private LocalDateTime arrTime;
    private List<Integer>  seats;
    private String nameArr;
    private String stateArr;
    private String nameDept;
    private String stateDept;
    private int message;
}
