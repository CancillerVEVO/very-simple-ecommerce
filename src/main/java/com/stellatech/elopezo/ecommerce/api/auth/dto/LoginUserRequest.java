package com.stellatech.elopezo.ecommerce.api.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequest {
        private String username;
        private String password;
}