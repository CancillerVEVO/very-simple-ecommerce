package com.stellatech.elopezo.ecommerce.services.security;

public interface EncryptService {
    String encryptPassword(String password);
    boolean checkPassword(String password, String hashedPassword);
}
