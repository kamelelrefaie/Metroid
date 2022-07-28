package com.metroid.metroid.train.station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station,Integer> {

    Station findByName(String name);

}
