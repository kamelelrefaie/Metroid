package com.metroid.metroid.train.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    Optional<Seat> findBySeatNumber(int seat);

    @Query(value = "select seat.id as id , seat.seat_number as seatNumber , seat.ticket_id as ticket from seat where ticket_id =?1",
            nativeQuery = true)
    List<SeatProjection> findByTicket(long id);
}
