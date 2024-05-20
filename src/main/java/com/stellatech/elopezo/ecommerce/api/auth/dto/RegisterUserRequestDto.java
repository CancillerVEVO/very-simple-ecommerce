package com.stellatech.elopezo.ecommerce.api.auth.dto;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterUserRequestDto {
    @NotBlank(message = "El campo username es requerido")
    @Size(min = 3, max = 20, message = "El campo username debe tener entre 3 y 20 caracteres")
    private String username;


    @NotEmpty(message = "El campo email es requerido")
    @Email(message = "El campo email debe ser un email válido", flags = {Flag.CASE_INSENSITIVE})
    private String email;

    @NotEmpty(message = "El campo password es requerido")
    @Size(min = 6, max = 20, message = "El campo password debe tener entre 6 y 20 caracteres")
    private String password;
}
