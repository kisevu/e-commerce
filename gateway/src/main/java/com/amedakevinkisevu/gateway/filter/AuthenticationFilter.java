package com.amedakevinkisevu.gateway.filter;


import com.amedakevinkisevu.gateway.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private JWTUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange,chain)->{
            //check if it contains header or not in order to do validate token call
            if(routeValidator.isSecured.test(exchange.getRequest())){
                //check whether header contains token or not
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("missing authorization header...");
                }
                //if it contains then get that header...
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                //up to this point you have the header with you
                if(authHeader!=null && authHeader.startsWith("Bearer ")){
                    //remove 7 space
                    authHeader = authHeader.substring(7);
                    //authHeader now contains the actual token...
                }
                try{
                    //do REST call to auth-service
                    //it can work but imposes a security breach...
//                    restTemplate.getForObject("http://AUTH-SERVICE/validate?token"+authHeader,String.class);
                    jwtUtil.validateToken(authHeader);
                }catch (Exception ex){
                   log.info("inside the gateway, rest call failed.");
                   throw new RuntimeException("unauthorized access to requested service...");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config{

    }
}
