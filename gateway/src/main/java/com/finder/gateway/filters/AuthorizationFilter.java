package com.finder.gateway.filters;

import com.finder.gateway.client.AuthClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthorizationFilter extends ZuulFilter {
    private final AuthClient authClient;

    public AuthorizationFilter(AuthClient authClient) {
        this.authClient = authClient;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String header = request.getHeader("Authorization");

        if(header != null && !header.isEmpty()) {
            if(header.startsWith("Bearer ")) {
                String token = header.replace("Bearer ", "");
                if(!authClient.validateToken(token)){
                    setTokenNotValid(ctx);
                }
            } else {
                setTokenNotValid(ctx);
            }
        }

        return null;
    }

    private void setTokenNotValid(RequestContext ctx) {
        ctx.setResponseStatusCode(403);
        ctx.setResponseBody("Token is not valid");
        ctx.setSendZuulResponse(false);
    }
}
