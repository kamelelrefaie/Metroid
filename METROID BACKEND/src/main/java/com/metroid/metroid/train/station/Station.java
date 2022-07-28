package com.metroid.metroid.train.station;

import com.metroid.metroid.train.trip.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Station {
    @Id
    private int id;
    @Column(unique = true)
    private String name;
    private String city;
    private String state;

}
