package com.finder.utils.jwt;

import io.jsonwebtoken.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private static String secret;
    private static long bearerValidityInMilliseconds;
    private static long refreshValidityInMilliseconds;

    public static String createToken(String username, Map<String, Object> map) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.putAll(map);

        Date now = new Date();
        Date validity = new Date(now.getTime() + bearerValidityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static String createRefreshToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshValidityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshValidityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static Authentication getAuthentication(String token){
        List<SimpleGrantedAuthority> p = new ArrayList();

        for (String permission : getPermissions(token)){
            p.add(new SimpleGrantedAuthority(permission));
        }

        Map<String, Object> user = new HashMap<>();
        user.put("username", getUsername(token));
        user.put("permissions", p);

        return new UsernamePasswordAuthenticationToken(user, "", p);
    }

    public static String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public static List<String> getPermissions(String token) {
        return (List<String>) Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("scope");
    }

    public static String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public static boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Value("${jwt.token.secret}")
    public void setSecret(String secret){
        JwtTokenProvider.secret = secret;
    };
    @Value("${jwt.token.bearer.expired}")
    public void setBearerValidityInMilliseconds(long bearerValidityInMilliseconds) {
        JwtTokenProvider.bearerValidityInMilliseconds = bearerValidityInMilliseconds;
    }
    @Value("${jwt.token.refresh.expired}")
    public void setRefreshValidityInMilliseconds(long refreshValidityInMilliseconds){
        JwtTokenProvider.refreshValidityInMilliseconds = refreshValidityInMilliseconds;
    };

}
