package com.metroid.metroid.train.station;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/home/station")
public class StationController {
    @Autowired
    private final StationService stationService;
    @GetMapping
    public Map<String,Integer> getStations (@RequestParam("from") String from,
                                           @RequestParam("to") String to){
        return stationService.getStations(from,to);
    }
}
