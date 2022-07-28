package com.metroid.metroid.train.ticket;

import com.metroid.metroid.login_cycle.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {


    @Query(value = "select * from ticket where app_user =?1",
            nativeQuery = true)
    Optional<Ticket> findByAppUser(long id);

    @Query(value = "select trip.id as id,trip.arr_time as arrTime , trip.dept_time as deptTime, ticket.date as date , ticket.price as price from trip join ticket on trip.id = ticket.trip_id where ticket.app_user=?1",
            nativeQuery = true)
    TicketAndTripModel getTicketAndTrip(long id);


    @Query(value = "select trip.destination_id as destinationId ,trip.source_id as sourceId from trip join ticket on trip.id = ticket.trip_id where ticket.app_user=?1",
            nativeQuery = true)
    DestAndSrcModel getDestAndSrc(long id);

    @Query(value = "select id from ticket where app_user=?1",
            nativeQuery = true)
    long getTicketId(long id);

    @Transactional
    @Modifying
    void deleteByAppUser(AppUser appUser);


}
