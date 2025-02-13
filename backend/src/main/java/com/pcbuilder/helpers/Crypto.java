package com.pcbuilder.helpers;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;


public class Crypto {
    private static Logger logger;
    private static Config config;
    private static SignatureAlgorithm signatureAlgorithm;


    Crypto(Logger givenLogger, Config givenConfig) {
        logger = givenLogger;
        config = givenConfig;
        signatureAlgorithm = SignatureAlgorithm.HS256;
        logger.info("Crypto", "Crypto initialized.");
    }

    public static String generateToken(String id, String issuer, String subject, long ttlMillis) {
        try {
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            byte[] keySecretBytes = DatatypeConverter.parseBase64Binary(config.getSetting("Server", "SecureKey"));
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
            logger.error("Crypto.generateToken", "Error creating JWT token.");
            return null;
        }
    }

    public static Claims verifyToken(String token, String expectedSubject) {
        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            byte[] keySecretBytes = DatatypeConverter.parseBase64Binary(config.getSetting("Server", "SecureKey"));
            Key signingKey = new SecretKeySpec(keySecretBytes, signatureAlgorithm.getJcaName());

            Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
            if (claims.getSubject().equals(expectedSubject)) {
                return claims;
            }
            return null;

        } catch (Exception e) {
            logger.error("Crypto.verifyToken", "Error verifying JWT token.");
            return null;
        }
    }
}
