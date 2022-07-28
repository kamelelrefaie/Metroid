package com.metroid.metroid.login_cycle.registration;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class UpdateProfileRequest {
    private final String username;
    private final String email;
    private final int phone;
    private final String password;
}


