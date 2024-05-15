package com.stellatech.elopezo.ecommerce.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncrypServiceImpl implements EncryptService{

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public String encryptPassword(String password) {
            return passwordEncoder.encode(password);
        }

        @Override
        public boolean checkPassword(String password, String hashedPassword) {
            return passwordEncoder.matches(password, hashedPassword);
        }

}
