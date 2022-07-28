package com.metroid.metroid.metro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MetroTripGetModel {
    private int price;
    private String fromStation;
    private String toStation;
    private LocalDate date;
}
