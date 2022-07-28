package com.metroid.metroid.train.trip;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@AllArgsConstructor
@Service
public class TripService {
    private final TripRepository tripRepository;
    private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    public List<Map<String, String>> getTripTime(int sourceId, int destId) {
        var listOfTrips = tripRepository.findBySourceIdAndDestinationIdOrderByArrTimeAsc(sourceId, destId);
        List<Map<String, String>> listMap = new ArrayList<>();
        for (var p : listOfTrips) {
            Map<String, String> map = new HashMap<>();

            var getArrTime = p.getArrTime();
            var getDeptTime = p.getDeptTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
            String formatArrTime = getArrTime.format(formatter);
            String formatDeptTime = getDeptTime.format(formatter);

            map.put("arr", formatArrTime);
            map.put("dept", formatDeptTime);
            var id = String.valueOf(p.getId());
            map.put("id", id);
            listMap.add(map);
        }
        return listMap;
    }

    public List<Map<String, String>> getTripAtSpecificTime(int sourceId, int destId, LocalDate date) {

        var listOfTrips = tripRepository.findBySourceIdAndDestinationIdAndArrTimeOrderByArrTimeAsc(sourceId, destId, date);

        List<Map<String, String>> listMap = new ArrayList<>();
        for (Trip p :
                listOfTrips) {
            Map<String, String> map = new HashMap<>();

            var getArrTime = p.getArrTime();
            var getDeptTime = p.getDeptTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
            String formatArrTime = getArrTime.format(formatter);
            String formatDeptTime = getDeptTime.format(formatter);

            map.put("arr", formatArrTime);
            map.put("dept", formatDeptTime);
            var id = String.valueOf(p.getId());
            map.put("id", id);
            listMap.add(map);
        }
        return listMap;
    }


    public List<String> insertTrip(TripRequest tripRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        LocalDateTime arrTime = LocalDateTime.parse(tripRequest.getArrTime(), formatter);
        LocalDateTime dept = LocalDateTime.parse(tripRequest.getDeptTime(), formatter);
        var t = Trip.builder().arrTime(arrTime)
                .deptTime(dept).sourceId(tripRequest.getSourceId()).destinationId(tripRequest.getDestinationId()).build();
        tripRepository.save(t);
        var res = new ArrayList<String>();
        res.add("success");


        return res;
    }
}
