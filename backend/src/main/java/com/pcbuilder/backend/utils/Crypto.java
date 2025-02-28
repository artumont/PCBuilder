package com.pcbuilder.backend.utils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.*;
import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;

public class Crypto {
    private static Logger logger;
    private static Config config;
    private static SignatureAlgorithm signatureAlgorithm;


    public Crypto(Logger givenLogger, Config givenConfig) {
        logger = givenLogger;
        config = givenConfig;
        signatureAlgorithm = SignatureAlgorithm.HS256;
        logger.info("Crypto", "Crypto initialized.");
    }

    public String generateToken(String id, String issuer, String subject, long ttlMillis) {
        try {
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            byte[] keySecretBytes = DatatypeConverter.parseBase64Binary(config.getSetting("Security", "SecureKey"));
            Key signingKey = new SecretKeySpec(keySecretBytes, signatureAlgorithm.getJcaName());

            JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setIssuer(issuer)
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);

            if (ttlMillis > 0) {
                long expMillis = nowMillis + ttlMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp);
            }  

            return builder.compact();
        }
        catch (Exception e) {
            logger.error("Crypto.generateToken", "Error creating JWT token. " + e.getMessage());
            return null;
        }
    }

    public Claims verifyToken(String token, String expectedSubject) {
        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            byte[] keySecretBytes = DatatypeConverter.parseBase64Binary(config.getSetting("Security", "SecureKey"));
            Key signingKey = new SecretKeySpec(keySecretBytes, signatureAlgorithm.getJcaName());

            Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
            if (claims.getSubject().equals(expectedSubject)) {
                return claims;
            }
            return null;

        } catch (Exception e) {
            logger.error("Crypto.verifyToken", "Error verifying JWT token. " + e.getMessage());
            return null;
        }
    }

    public String getHashedString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (Exception e) {
            logger.error("Crypto.getHashedString", "Error hashing string: " + e.getMessage());
            return null;
        }
    }
}