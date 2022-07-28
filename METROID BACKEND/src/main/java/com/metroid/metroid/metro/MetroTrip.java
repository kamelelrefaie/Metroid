package com.metroid.metroid.metro;

import com.metroid.metroid.login_cycle.appuser.AppUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;



@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
@Entity
@Getter
public class MetroTrip {

    @SequenceGenerator(name = "metro_sequence", sequenceName = "metro_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "metro_sequence", strategy = GenerationType.SEQUENCE)
    private long id;

    private int price;
    private String fromStation;
    private String toStation;
    private LocalDate localDate;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(nullable = false , name = "app_user")
    private AppUser appUser;
}
