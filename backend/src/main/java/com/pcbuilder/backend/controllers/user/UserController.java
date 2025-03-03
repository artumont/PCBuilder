package com.pcbuilder.backend.controllers.user;

import java.sql.Connection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcbuilder.backend.utils.Crypto;
import com.pcbuilder.backend.utils.Logger;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger;
    private final Crypto crypto;

    public UserController(Logger givenLogger, Connection givenConnection, Crypto givenCrypto) {
        this.logger = givenLogger;
        this.crypto = givenCrypto;
    }
}
