package com.tokentest.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        
        
        converter.setJwtGrantedAuthoritiesConverter(new RoleBasedAuthorizer());

        // Customize the authorities converter if needed
        // JwtGrantedAuthoritiesConverter customConverter = new CustomJwtGrantedAuthoritiesConverter();
        // converter.setJwtGrantedAuthoritiesConverter(customConverter);

        return converter;
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/public/**").permitAll() // Public endpoints
                    .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2ResourceServer ->
                oauth2ResourceServer
                    .jwt(jwt ->
                        jwt
                            .jwtAuthenticationConverter(jwtAuthenticationConverter())
                            .decoder(jwtDecoder())));
              
        
        return http.build();
    }
    
    @Bean
    public JwtDecoder jwtDecoder() {
        // Create a NimbusJwtDecoder with the JWKS URI provided by Amazon Cognito
        //String jwksUri = "https://cognito-idp.us-east-1.amazonaws.com/YOUR_USER_POOL_ID/.well-known/jwks.json";
       
        String jwksUri = "https://cognito-idp.ap-south-1.amazonaws.com/ap-south-1_DvJMQyd30/.well-known/jwks.json"; 
        return NimbusJwtDecoder.withJwkSetUri(jwksUri)
            .build();
    }
}
