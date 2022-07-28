package com.metroid.metroid.train.ticket;

import com.metroid.metroid.login_cycle.appuser.AppUser;
import com.metroid.metroid.login_cycle.appuser.AppUserRepository;
import com.metroid.metroid.train.station.StationRepository;
import com.metroid.metroid.train.trip.Trip;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final AppUserRepository appUserRepository;
    private final StationRepository stationRepository;


    public Map<String, String> confirmTicket(long userId, int tripId, TicketModel ticketModel) {

        Map<String, String> map = new HashMap<>();

        var currentNumOfSeats = seatRepository.count();
        if (currentNumOfSeats > 24000) {
            map.put("message", "no seats available");
            return map;
        }
        try {

            Ticket ticket = new Ticket();
            ticket.setPrice(ticketModel.getPrice());
            ticket.setQuantity(ticketModel.getQuantity());
            ticket.setAppUser(new AppUser(userId));
            ticket.setTrip(new Trip(tripId));
            ticket.setDate(LocalDate.now());
            ticket.setTClass(ticketModel.getTClass());


            for (Seat s :
                    ticketModel.getSeats()) {
                Optional<Seat> checkSeat = seatRepository.findBySeatNumber(s.getSeatNumber());
                if (checkSeat.isPresent()) {
                    map.put("message", "seat:" + checkSeat.get().getSeatNumber() + " is taken choose another seat");
                    return map;
                }

            }

            for (Seat s :
                    ticketModel.getSeats()) {
                s.setTicket(ticket);
                seatRepository.save(s);
            }
        } catch (Exception e) {
            map.put("message", "something went wrong");
            return map;
        }
        map.put("message", "success");
        return map;

    }


    public Map<String, String> isSeatAvailable(int seat) {
        Map<String, String> map = new HashMap<>();

        Optional<Seat> checkSeat = seatRepository.findBySeatNumber(seat);
        if (checkSeat.isPresent()) {
            map.put("message", "seat:" + checkSeat.get().getSeatNumber() + " is taken choose another seat");
        } else {
            map.put("message", "success");
        }
        return map;
    }

    public TicketGetInfoResponse getTicketData(long id) {
        Map<String, Integer> map = new HashMap<>();
        TicketGetInfoResponse ticketGetInfoResponse = new TicketGetInfoResponse();
        Optional<Ticket> ticket = ticketRepository.findByAppUser(id);
        if (!ticket.isPresent()) {
            ticketGetInfoResponse.setMessage(0);
            return ticketGetInfoResponse;
        }
        try {
            //get name
            var name = appUserRepository.findFirstName(id);
            ticketGetInfoResponse.setFirstName(name);
            //get ticket and trip
            var ticketAndTrip = ticketRepository.getTicketAndTrip(id);

            ticketGetInfoResponse.setArrTime(ticketAndTrip.getArrTime());
            ticketGetInfoResponse.setDeptTime(ticketAndTrip.getDeptTime());
            ticketGetInfoResponse.setTripId(ticketAndTrip.getId());
            ticketGetInfoResponse.setPrice(ticketAndTrip.getPrice());
            ticketGetInfoResponse.setConfirmDate(ticketAndTrip.getDate());
            // get seats
            //get ticket id
            long ticketId = ticketRepository.getTicketId(id);
            //get seat list
            var seats = seatRepository.findByTicket(ticketId);
            List<Integer> listOfSeats = new ArrayList<>();
            for (SeatProjection s :
                    seats) {
                listOfSeats.add(s.getSeatNumber());
            }

            ticketGetInfoResponse.setSeats(listOfSeats);
            //get station name and state
            // get dest and src
            var destSrc = ticketRepository.getDestAndSrc(id);

            var stationArr = stationRepository.findById(destSrc.getSourceId()).get();
            var nameArr = stationArr.getName();
            var stateArr = stationArr.getState();
            ticketGetInfoResponse.setNameArr(nameArr);
            ticketGetInfoResponse.setStateArr(stateArr);

            var stationDept = stationRepository.findById(destSrc.getDestinationId()).get();
            var nameDept = stationDept.getName();
            var stateDept = stationDept.getState();
            ticketGetInfoResponse.setNameDept(nameDept);
            ticketGetInfoResponse.setStateDept(stateDept);

            ticketGetInfoResponse.setMessage(1);
            return ticketGetInfoResponse;
        } catch (Exception e) {
            ticketGetInfoResponse.setMessage(11);
            return ticketGetInfoResponse;
        }


    }

    public Seat toSeat(SeatProjection s) {
        return new Seat(s.getId(), s.getSeatNumber(), s.getTicket());
    }

    public Map<String, String> deleteTicket(long id) {
        Map<String, String> map = new HashMap<>();

        Optional<Ticket> ticket = ticketRepository.findByAppUser(id);
        if (ticket.isPresent()) {
            ticketRepository.deleteByAppUser(new AppUser(id));
            map.put("message", "Deleted Successfully");

        } else {
            map.put("message", "there are no Tickets to Delete");
        }

        return map;
    }
}
