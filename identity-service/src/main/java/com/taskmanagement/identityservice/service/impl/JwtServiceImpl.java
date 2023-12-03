package com.taskmanagement.identityservice.service.impl;

import com.taskmanagement.identityservice.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    private static final Integer ACCESS_TOKEN_EXPIRES_IN = 60;

    @Value("${secret.key}")
    private String secretKey;

    @Override
    public void validateToken(final String jwt) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(jwt);
    }

    @Override
    public String generateToken(Map<String, Object> claims, String email) {
        final long currentTimeMillis = System.currentTimeMillis();
        final Date accessTokenIssuedAt = new Date(currentTimeMillis);
        final Date accessTokenExpiresAt = DateUtils.addMinutes(accessTokenIssuedAt, ACCESS_TOKEN_EXPIRES_IN);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(accessTokenIssuedAt)
                .setExpiration(accessTokenExpiresAt)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
