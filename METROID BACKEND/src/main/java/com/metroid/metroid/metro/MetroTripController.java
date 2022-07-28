package com.metroid.metroid.metro;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/home/metro/add/trip")
@AllArgsConstructor
public class MetroTripController {
    private final MetroTripService metroTripService;
    @PostMapping
    public Map<String,String> postTrip (@RequestBody MetroTripRequest metroTripRequest){
        return  metroTripService.postTrip(metroTripRequest);
    }

    @GetMapping
    public Map<String,String> getTripAndCredit (@RequestParam("user_id") long userId){
        return  metroTripService.getTripAndCredits(userId);
    }

    @GetMapping("/getTrips")
    public List<MetroTripGetModel> getMetroTrips(@RequestParam("user_id") long userId){
        return  metroTripService.getMetroTrips(userId);
    }

}
