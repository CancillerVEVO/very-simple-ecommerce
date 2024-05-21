package com.stellatech.elopezo.ecommerce.api.auth;

import com.stellatech.elopezo.ecommerce.api.auth.dto.LoginResponse;
import com.stellatech.elopezo.ecommerce.api.auth.dto.LoginUserRequestDto;
import com.stellatech.elopezo.ecommerce.api.auth.dto.RegisterUserRequestDto;
import com.stellatech.elopezo.ecommerce.api.users.User;
import com.stellatech.elopezo.ecommerce.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }


    @PostMapping("/signup")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterUserRequestDto registerUserRequest) {
        User registeredUser = authenticationService.signUp(registerUserRequest);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginUserRequestDto loginUserRequest) {
        User authenticatedUser = authenticationService.authenticate(loginUserRequest);

        String jwt = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwt);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
