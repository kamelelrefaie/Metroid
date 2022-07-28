package com.metroid.metroid.train.ticket;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {
    @SequenceGenerator(name = "seat_sequence", sequenceName = "seat_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "seat_sequence", strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(unique = true)
    private int seatNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;



}
