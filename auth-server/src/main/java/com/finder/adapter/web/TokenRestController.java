package com.finder.adapter.web;

import com.finder.port.models.request.TokenRequestModel;
import com.finder.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/v1/token")
@RequiredArgsConstructor
public class TokenRestController {

    @GetMapping("/validate")
    public boolean validateAccessToken(@RequestParam("token") String token) {
        return JwtTokenProvider.validateToken(token);
    }

    @PostMapping("/validate")
    public boolean validateAccessToken(@RequestBody TokenRequestModel token) {
        return JwtTokenProvider.validateToken(token.token);
    }

}
