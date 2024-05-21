package com.stellatech.elopezo.ecommerce.api.auth;

import com.stellatech.elopezo.ecommerce.api.auth.dto.LoginUserRequestDto;
import com.stellatech.elopezo.ecommerce.api.auth.dto.RegisterUserRequestDto;
import com.stellatech.elopezo.ecommerce.api.auth.exceptions.UserAuthenticationException;
import com.stellatech.elopezo.ecommerce.api.users.User;
import com.stellatech.elopezo.ecommerce.api.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public User signUp(RegisterUserRequestDto input) {
        User user = new User();
        user.setUsername(input.getUsername());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userService.registerUser(user);
    }

    public User authenticate(LoginUserRequestDto input) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword())
            );

            return userService.findByUsername(input.getUsername());

        } catch (Exception e) {
            throw new UserAuthenticationException("Usuario o contraseña incorrectos");
        }
    }
}
