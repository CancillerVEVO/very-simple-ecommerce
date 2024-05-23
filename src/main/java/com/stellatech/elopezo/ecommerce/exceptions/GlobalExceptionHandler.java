package com.stellatech.elopezo.ecommerce.exceptions;

import com.stellatech.elopezo.ecommerce.api.auth.exceptions.UserAuthenticationException;
import com.stellatech.elopezo.ecommerce.api.order_items.exceptions.InsufficientProductStockException;
import com.stellatech.elopezo.ecommerce.api.orders.exceptions.OrderNotFoundException;
import com.stellatech.elopezo.ecommerce.api.orders.exceptions.OrderPermissionException;
import com.stellatech.elopezo.ecommerce.api.products.exceptions.ProductNotFoundException;
import com.stellatech.elopezo.ecommerce.api.products.exceptions.ProductPermissionException;
import com.stellatech.elopezo.ecommerce.api.users.exceptions.UserAlredyExistsException;
import com.stellatech.elopezo.ecommerce.api.users.exceptions.UserNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler  {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

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

    @ExceptionHandler({OrderNotFoundException.class})
    public ResponseEntity<Object> handleOrderNotFound(OrderNotFoundException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({OrderPermissionException.class})
    public ResponseEntity<Object> handleOrderPermission(OrderPermissionException ex) {
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
        logger.error("Error interno del servidor", ex);
        logger.info(ex.getClass().getSimpleName());
        Map<String, String> body = new HashMap<>();
        body.put("message","Error interno del servidor");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("message", "El parámetro " + ex.getName() + " debe ser de tipo " + Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InsufficientProductStockException.class})
    public ResponseEntity<Object> handleInsufficientProductStock(InsufficientProductStockException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}


