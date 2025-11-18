package com.zosh.projectmanagmentsystem.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;

public class JwtProvider {
    static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
//    Claims claims = Jwts.parserBuilder()
//            .setSigningKey(key)
//            .build()
//            .parseClaimsJws(jwt)
//            .getBody();
//
//    String email = String.valueOf(claims.get("email"));
//    String authority = String.valueOf(claims.get("authority"));

    public static String generateToken(Authentication authentication){
        return Jwts.builder()
                .setExpiration(new Date())
                .setIssuedAt(new Date(new Date().getTime()+86400000))
                .claim("email", authentication.getName())
                .signWith(key)
                .compact();
    }

    public static String getEmailFormToken(String jwt){
            Claims claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jwt)
            .getBody();

        return String.valueOf(claims.get("email"));
    }

}
