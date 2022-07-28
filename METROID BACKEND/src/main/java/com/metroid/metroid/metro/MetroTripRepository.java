package com.metroid.metroid.metro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetroTripRepository extends JpaRepository<MetroTrip, Long> {
    @Query(value = "select count(?1) from metro_trip", nativeQuery = true)
    int getTripsCount(long id);

    @Query(value = "select * from metro_trip where app_user = ?1", nativeQuery = true)
    Optional<List<MetroTrip>> findTripsById(long id);
}
