package com.stellatech.elopezo.ecommerce.exceptions;

import com.stellatech.elopezo.ecommerce.api.auth.exceptions.UserAuthenticationException;
import com.stellatech.elopezo.ecommerce.api.products.exceptions.ProductNotFoundException;
import com.stellatech.elopezo.ecommerce.api.products.exceptions.ProductPermissionException;
import com.stellatech.elopezo.ecommerce.api.users.exceptions.UserAlredyExistsException;
import com.stellatech.elopezo.ecommerce.api.users.exceptions.UserNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.access.AccessDeniedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserAlredyExistsException.class})
    public ResponseEntity<Object> handleUserAlreadyExists(UserAlredyExistsException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserAuthenticationException.class})
    public ResponseEntity<Object> handleUserAuthentication(UserAuthenticationException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<Object> handleProductNotFound(ProductNotFoundException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ProductPermissionException.class})
    public ResponseEntity<Object> handleProductPermission(ProductPermissionException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({JwtException.class})
    public ResponseEntity<Object> handleJwtException(JwtException ex) {
        if (ex instanceof MalformedJwtException) {
            return new ResponseEntity<>(Map.of("message", "Token mal formado"), HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof ExpiredJwtException) {
            return new ResponseEntity<>(Map.of("message", "Token expirado"), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(Map.of("message", "Token inválido"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        Map<String, String> body = new HashMap<>();
        body.put("message","Error interno del servidor");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


