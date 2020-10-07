package com.finder.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("auth-server")
public interface AuthClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/rest/v1/token/validate"
    )
    boolean validateToken(@RequestParam("token") String token);

}
