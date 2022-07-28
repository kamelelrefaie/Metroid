package com.metroid.metroid.train.ticket;

import com.metroid.metroid.login_cycle.appuser.AppUser;
import com.metroid.metroid.train.trip.Trip;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Ticket {
    @SequenceGenerator(name = "ticket_sequence", sequenceName = "ticket_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "ticket_sequence", strategy = GenerationType.SEQUENCE)
    private long id;
    private String tClass;
    private int price;
    private LocalDate date;
    @OneToMany(mappedBy = "ticket",orphanRemoval = true)
    private List<Seat> seats;
    private int quantity;


    @OneToOne
    @JoinColumn(nullable = false,name="app_user",referencedColumnName = "id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(nullable = false,name = "trip_id",referencedColumnName = "id")
    private Trip trip;

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
