package com.edu.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.crypto.spec.SecretKeySpec;
import java.util.List;

@Component
public class VailidateTokenFilter extends AbstractGatewayFilterFactory<VailidateTokenFilter.Config> {
    public VailidateTokenFilter() {
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
        //Custom Pre Filter. Suppose we can extract JWT and perform Authentication
        return (exchange, chain) -> {
            String authToken = exchange.getRequest().getHeaders().get("Authorization").get(0).replace("Bearer ","");
            System.out.println("First pre filter" + authToken);

            System.out.println(jwtDecoder().decode(authToken));
            //Custom Post Filter.Suppose we can call error response handler based on error code.
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("First post filter");
            }));
        };
    }

    public static class Config {
        // Put the configuration properties
        
    }
    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        byte[] key = "123456789012345678901234567890AB".getBytes();
        SecretKeySpec originalKey = new SecretKeySpec(key, 0, key.length, "HS256");

        return NimbusReactiveJwtDecoder.withSecretKey(originalKey).build();
    }
}
