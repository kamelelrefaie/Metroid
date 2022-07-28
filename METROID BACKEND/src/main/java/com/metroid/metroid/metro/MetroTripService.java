package com.metroid.metroid.metro;

import com.metroid.metroid.login_cycle.appuser.AppUser;
import com.metroid.metroid.login_cycle.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class MetroTripService {
    @Autowired
    private final MetroTripRepository metroTripRepository;

    @Autowired
    private final AppUserRepository appUserRepository;


    @Transactional
    @Modifying
    public Map<String, String> postTrip(MetroTripRequest metroTripRequest) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Optional<AppUser> getAppUserOptional = appUserRepository.findById(metroTripRequest.getAppUserId());
            AppUser getAppUser = getAppUserOptional.get();

            getAppUser.setCredits(getAppUser.getCredits() - metroTripRequest.getPrice());

            MetroTrip metroTrip = MetroTrip.builder()
                    .fromStation(metroTripRequest.getFrom()).toStation(metroTripRequest.getTo())
                    .localDate(LocalDate.now())
                    .price(metroTripRequest.getPrice())
                    .appUser(getAppUser)
                    .build();

            System.out.println(metroTrip.toString());

            appUserRepository.save(getAppUser);
            metroTripRepository.save(metroTrip);

            map.put("message", "success");
            return map;

        } catch (Exception e) {
            System.out.println(e.toString());
            map.put("message", "error");
            return map;
        }

    }

    public Map<String, String> getTripAndCredits(long userId) {
        Map<String, String> map = new HashMap<String, String>();

        try {
            Optional<AppUser> getAppUserOptional = appUserRepository.findById(userId);
            AppUser getAppUser = getAppUserOptional.get();

            map.put("credit", String.valueOf(getAppUser.getCredits()));


            var count = metroTripRepository.getTripsCount(userId);
            map.put("trips", String.valueOf(count));
            map.put("message", "success");
            return map;
        } catch (Exception e) {
            map.put("message", "success");
            return map;
        }

    }

    public List<MetroTripGetModel> getMetroTrips(long userId) {
        var metroTripList = new ArrayList<MetroTripGetModel>();

        var trips = metroTripRepository.findTripsById(userId);

        try {
            if (trips.isPresent()) {
                for (var metroTrip :
                        trips.get()) {
                    var item = new MetroTripGetModel(metroTrip.getPrice(), metroTrip.getFromStation(),
                            metroTrip.getToStation(), metroTrip.getLocalDate());
                    metroTripList.add(item);
                }
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return metroTripList;
    }
}
