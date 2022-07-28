package com.metroid.metroid.login_cycle.appuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPhone {

    @SequenceGenerator(name = "user_phone_sequence", sequenceName = "user_phone_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "user_phone_sequence", strategy = GenerationType.SEQUENCE)
    private long userPhoneId;
    private int code;
    private int number;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;
}
