package com.pccw.backend.modules.user;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class PasswordGenerator {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String generatePassword() {
        var password = UUID.randomUUID().toString().replace("-", "");
        return passwordEncoder.encode(password);
    }

}
