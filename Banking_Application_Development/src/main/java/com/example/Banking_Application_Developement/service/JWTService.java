package com.example.Banking_Application_Developement.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTService {

    private static final String SECRET_KEY = "786D5SEA56G6DO0P1T52U5Z77A0II295BXEW3318MV251Kl310581IXMFWI286IX";
//    private static final String SECRET_KEY = "6QP+6UyKPPn69L51WLy7sNGVLzUywFFWDuAVhlxwP22fKyZf8MSOvDzMvIer5S+a9l06gdN5kTZ+KmdDeVvYxA==";

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(UserDetails userDetails){
        return  createFreshToken(new HashMap<>(), userDetails);
    }

    private String createFreshToken(Map<String, Object> mapOfClaims, UserDetails userDetails) {
        return Jwts.builder()
                .addClaims(mapOfClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .setIssuer("Banking Application 1.0")
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }
}
