package com.metroid.metroid.train.trip;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/home/trip")
public class TripController {

    private final TripService tripService;

    @GetMapping
    public List<Map<String, String>> getTripTime(@RequestParam("source") int sourceId, @RequestParam("dest") int destId) {
        return tripService.getTripTime(sourceId, destId);
    }

    @GetMapping("/date")
    public List<Map<String, String>> getTripAtSpecificTime(@RequestParam("source") int sourceId, @RequestParam("dest") int destId, @RequestParam("arr")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate arr) {
        return tripService.getTripAtSpecificTime(sourceId, destId, arr);
    }

    @PostMapping("/insert")
    public List<String> insertTrip(@RequestBody TripRequest tripRequest) {
        return tripService.insertTrip(tripRequest);
    }
}
