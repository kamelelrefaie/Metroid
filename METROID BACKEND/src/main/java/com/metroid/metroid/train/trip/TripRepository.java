package com.metroid.metroid.train.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findBySourceIdAndDestinationIdOrderByArrTimeAsc(int sourceId, int destinationId);



    @Query(value = "SELECT * FROM trip WHERE source_id=:sourceId AND destination_id=:destinationId AND DATE(arr_time) =:arr", nativeQuery = true)
    List<Trip> findBySourceIdAndDestinationIdAndArrTimeOrderByArrTimeAsc(int sourceId, int destinationId, LocalDate arr);


}
