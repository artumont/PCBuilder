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

    public static String generateAuthToken(String id, String issuer, long ttlMillis) {
        try {
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            byte[] keySecretBytes = DatatypeConverter.parseBase64Binary(config.getSetting("Server", "SecureKey"));
            Key signingKey = new SecretKeySpec(keySecretBytes, signatureAlgorithm.getJcaName());

            JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

            if (ttlMillis > 0) {
                long expMillis = nowMillis + ttlMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp);
            }  

            return builder.compact();
        }
        catch (Exception e) {
            logger.error("Crypto.generateAuthToken", "Error creating JWT token.");
            return null;
        }
    }

    public static boolean verifyAuthToken(String token) {
        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            byte[] keySecretBytes = DatatypeConverter.parseBase64Binary(config.getSetting("Server", "SecureKey"));
            Key signingKey = new SecretKeySpec(keySecretBytes, signatureAlgorithm.getJcaName());

            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody() != null;
        } catch (Exception e) {
            logger.error("Crypto.verifyAuthToken", "Error verifying JWT token.");
            return false;
        }
    }
}
