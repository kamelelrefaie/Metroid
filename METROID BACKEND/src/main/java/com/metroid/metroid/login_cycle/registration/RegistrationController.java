package com.metroid.metroid.login_cycle.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;
    private final ForgetPasswordService forgetPasswordService;
    @PostMapping
    public Map<String,String> register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @PutMapping(path = "edit")
    public Map<String,String> updateProfile(@RequestParam("id") long id,@RequestBody UpdateProfileRequest request){
        return registrationService.updateProfile(request,id);
    }

    @PostMapping("/forget")
    public Map<String,String> forgetPassword(@RequestParam("email") String email){
        return forgetPasswordService.forgetPassword(email);
    }

    @GetMapping("/fetch")
    public Map<String,String> getNameAndId(@RequestParam("email") String email){
        return forgetPasswordService.getNameAndId(email);
    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
