package com.finder.port.models.response;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;

@Builder
public class AccessTokenResponse {
    public String accessToken;
    @Value("${jwt.token.bearer.expired}")
    public String expiresIn;
    public String refreshToken;
}
