package com.metroid.metroid.train.ticket;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/home/ticket")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/confirm")
    public Map<String, String> buyTicket(@RequestParam("appUser") long userId, @RequestParam("tripId") int tripId,
                                         @RequestBody TicketModel ticketModel
    ) {
        return ticketService.confirmTicket(userId, tripId, ticketModel);
    }

    @GetMapping("/seats")
    public Map<String, String> isSeatAvailable(@RequestParam("seat") int seat
    ) {
        return ticketService.isSeatAvailable(seat);
    }

    @GetMapping("/getTicketData")
    public TicketGetInfoResponse getTicketData (@RequestParam("id") long id){
        return ticketService.getTicketData(id);
    }

    @DeleteMapping("/delete")
    public Map<String,String> deleteTicket (@RequestParam("id") long id){
        return ticketService.deleteTicket(id);
    }
}
