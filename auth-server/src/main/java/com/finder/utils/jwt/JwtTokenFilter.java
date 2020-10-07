package com.finder.utils.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        String token = JwtTokenProvider.resolveToken((HttpServletRequest) req);

        if(token != null && JwtTokenProvider.validateToken(token)) {
            Authentication authentication = JwtTokenProvider.getAuthentication(token);

            if(authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(req, res);
    }
}
