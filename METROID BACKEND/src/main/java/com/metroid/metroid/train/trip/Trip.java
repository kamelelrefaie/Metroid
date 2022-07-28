package com.metroid.metroid.train.trip;

import com.metroid.metroid.train.station.Station;
import com.metroid.metroid.train.ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Builder
public class Trip {

    @SequenceGenerator(name = "trip_sequence", sequenceName = "trip_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "trip_sequence", strategy = GenerationType.SEQUENCE)
    private long id;
    private LocalDateTime deptTime;
    private LocalDateTime arrTime;

    private int sourceId;
    private int destinationId;

    @OneToMany(mappedBy = "trip")
    private List<Ticket> tickets;

    public Trip(long id) {
        this.id = id;
    }
}
