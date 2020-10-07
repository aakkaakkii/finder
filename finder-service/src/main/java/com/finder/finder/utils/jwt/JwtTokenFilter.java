package com.finder.finder.utils.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtTokenFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        String token = null;

        String bearerToken = ((HttpServletRequest) req).getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            token =  bearerToken.substring(7);
        }

        if(token != null) {
            List<SimpleGrantedAuthority> permissions = ((List<String>)Jwts.parser().parseClaimsJws(token).getBody().get("scope"))
                    .stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

            Map<String, Object> user = new HashMap<>();
            user.put("username", Jwts.parser().parseClaimsJws(token).getBody().getSubject());
            user.put("permissions", permissions);
            user.put("id", Jwts.parser().parseClaimsJws(token).getBody().get("userId"));

            Authentication authentication =  new UsernamePasswordAuthenticationToken(user, "", permissions);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(req, res);
    }
}
