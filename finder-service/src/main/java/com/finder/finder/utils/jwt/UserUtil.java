package com.finder.finder.utils.jwt;

import org.springframework.security.core.Authentication;

import java.util.HashMap;

public class UserUtil {
    public static Long getUserIdFromAuthentication(Authentication authentication) {
        return (Long) ((HashMap<String, Object>) authentication.getPrincipal()).get("id");
    }
}
