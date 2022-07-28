package com.metroid.metroid.train.trip;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TripRequest {
    private String deptTime;
    private String arrTime;

    private int sourceId;
    private int destinationId;
}
