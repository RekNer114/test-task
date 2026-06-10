package me.vmachohan.auth_api.controller;

import lombok.RequiredArgsConstructor;
import me.vmachohan.auth_api.dto.login.LoginRequest;
import me.vmachohan.auth_api.dto.login.LoginResponse;
import me.vmachohan.auth_api.dto.RegisterRequest;
import me.vmachohan.auth_api.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request){
        authService.register(request.email(), request.password());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")

    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        String token = authService.login(request.email(), request.password());
        return ResponseEntity.ok(new LoginResponse(token));
    }

}
