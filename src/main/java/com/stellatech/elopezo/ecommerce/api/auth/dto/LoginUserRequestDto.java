package com.stellatech.elopezo.ecommerce.api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserRequestDto {

        @NotBlank(message = "El campo username es requerido")
        private String username;
        @NotBlank(message = "El campo password es requerido")
        private String password;
}
