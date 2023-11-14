package com.amedakevinkisevu.gateway.filter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    //bypass the below requests in the API gateway
    //For the rest of the requests going through the gateway needs passing the token.
    //The token needs to be validated and so, therefore it doesn't make much sense
    //to white label the /validateToken endpoint for that matter
    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/token",
            "/eureka"
    );
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
