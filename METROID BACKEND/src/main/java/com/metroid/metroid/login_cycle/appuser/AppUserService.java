package com.metroid.metroid.login_cycle.appuser;

import com.metroid.metroid.login_cycle.registration.UpdateProfileRequest;
import com.metroid.metroid.login_cycle.registration.token.ConfirmationToken;
import com.metroid.metroid.login_cycle.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserPhoneRepository userPhoneRepository;
    private final static String USER_NOT_FOUND_MSG =
            "User with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository
                .findByEmail(email).orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    @Transactional
    public String signUpUser(AppUser appUser) {

        boolean userExit = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if (userExit) {
            var user = appUserRepository.findByEmail(appUser.getEmail()).get();
            if (!user.getConfirmationToken().isEmpty()) {
                String token = UUID.randomUUID().toString();
                ConfirmationToken confirmationToken = new ConfirmationToken(
                        token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15)
                        , appUser);
                confirmationTokenService.saveConfirmationToken(confirmationToken);
            } else {
                throw new IllegalStateException("Email already taken");
            }
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);
        // send confirmation and token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15)
                , appUser);
        confirmationTokenService.saveConfirmationToken(confirmationToken);


        return token;
    }


    @Transactional
    public HashMap<String, String> updateUser(UpdateProfileRequest updateProfileRequest, long id) {
        HashMap<String, String> map = new HashMap<>();

        AppUser user = appUserRepository.findById(id).orElseThrow();

        user.setFirstName(updateProfileRequest.getUsername());
        var code = 20;
        var number = updateProfileRequest.getPhone();
        var numbers = new ArrayList<UserPhone>();
        var userPhone = UserPhone.builder().appUser(user).code(code).number(number).build();

        numbers.add(userPhone);
        userPhoneRepository.save(userPhone);
        user.setUserPhones(numbers);

        user.setEmail(updateProfileRequest.getEmail());

        String encodedPassword = bCryptPasswordEncoder.encode(updateProfileRequest.getPassword());
        user.setPassword(encodedPassword);

        map.put("message","success");


        return map;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

}
