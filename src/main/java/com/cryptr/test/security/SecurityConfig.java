package com.cryptr.test.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Value("${cryptr.audience}")
  private String audience;

  @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
  String jwkSetUri;

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuer;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
      .cors().and() // cors should not be present in production mode
      .authorizeRequests()
      .mvcMatchers("/").permitAll()
      .mvcMatchers("/api/v1/").permitAll()
      .mvcMatchers("/api/v1/secured-resource/").authenticated()
      .mvcMatchers("/api/v1/secured-resource").authenticated()
      .and().oauth2ResourceServer().jwt();
  }

  JwtDecoder jwtDecoder() {
    NimbusJwtDecoder decoder = NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();

    OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
    OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);
    decoder.setJwtValidator(withAudience);

    return decoder;
  }
}