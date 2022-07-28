package com.metroid.metroid.train.station;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class StationService {
    private final StationRepository stationRepository;
    public Map<String, Integer> getStations(String from, String to) {
        var fromDatabase = stationRepository.findByName(from);
        var toDatabase = stationRepository.findByName(to);

        Map<String,Integer> map = new HashMap<>();
        map.put("from",fromDatabase.getId());
        map.put("to", toDatabase.getId());
        return map;

    }
}
