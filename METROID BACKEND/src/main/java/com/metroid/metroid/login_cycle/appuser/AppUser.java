package com.metroid.metroid.login_cycle.appuser;

import com.metroid.metroid.login_cycle.registration.token.ConfirmationToken;
import com.metroid.metroid.metro.MetroTrip;
import com.metroid.metroid.train.ticket.Ticket;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "user_sequence", strategy = GenerationType.SEQUENCE)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private float credits=0;
    @Enumerated(EnumType.STRING)
    private AppUserRule appUserRule;
    private Boolean locked = false;
    private Boolean enabled = false;

    @OneToMany(mappedBy = "appUser")
    private List<ConfirmationToken> confirmationToken;

    @OneToMany(mappedBy = "appUser")
    private List<MetroTrip> metroTrips;

    @OneToMany(mappedBy = "appUser")
    private List<UserPhone> userPhones;

    @OneToOne(mappedBy = "appUser")
    private Ticket ticket;


    public AppUser(String fistName,
                   String lastName,
                   String email,
                   String password,
                   AppUserRule appUserRule) {
        this.firstName = fistName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRule = appUserRule;

    }

    public AppUser(long id) {
        this.id = id;
    }

    public AppUser(long id,float credits) {
        this.id = id;
        this.credits = credits;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRule.name());

        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
