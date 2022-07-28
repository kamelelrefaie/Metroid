package com.metroid.metroid.metro;

import com.metroid.metroid.login_cycle.appuser.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MetroTripRequest {

    private int price;
    private String from;
    private String to;
    private long appUserId;

}
