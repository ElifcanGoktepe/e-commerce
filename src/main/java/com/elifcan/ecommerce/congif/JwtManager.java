package com.elifcan.ecommerce.congif;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class JwtManager {

    /**
     * Requirement to create Token
     * 1 - SecretKey: used to encrypt token
     * IMPORTANT NOTES!!!
     * SecretKey mustn't be written in code
     * 2 - Issuer: jwt token owner information
     * 3 - IssuerAt: creation timestamp by issuer
     * 4 - ExpireAt: expire date of token
     * 5 - Claim Object: object includes key-value type information. public information.
     * 6 - Sign: necessary for sign to related token. determine the crypto algorithm and create crypto
     */

    @Value("${my-jwt.secret-key}")
    private String secretKey;
    private String issuer = "Elifcanmg";
    private Long expiredDate = 1000L * 45;
    public String createToken(Long userId){
        String token = "";
        Long now = System.currentTimeMillis(); // current time in terms of Long
        Date issuerAt = new Date(now);
        Date expiredAt = new Date(now + expiredDate);
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        token = JWT.create()
                .withAudience()
                .withIssuer(issuer)
                .withIssuedAt(issuerAt)
                .withExpiresAt(expiredAt)
                .withClaim("userId", userId)
                .withClaim("ECommerce", "New application")
                .withClaim("log","current time : " + (new Date()))
                .sign(algorithm);
        return token;
    }

    public Optional<Long> validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token); // checks the expiration and owner of token
            if(Objects.isNull(decodedJWT)) // if decoded is emty
                return Optional.empty();
            Long userId = decodedJWT.getClaim("userId").asLong(); // claim object is taken in terms of Long
            return Optional.of(userId); // return the value in terms of optional
        }catch(Exception e){
            return Optional.empty();
        }
    }
}
