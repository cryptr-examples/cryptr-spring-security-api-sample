# Cryptr Spring Security API Sample

## 03 Configure Security

### A - Update Application YAML file for Spring security config

```yaml
# src/main/resources.application.yml
cryptr:
  audience: http://localhost:3000 # Fill here with audience we provided you
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://auth.cryptr.eu/t/shark-academy # change with provided value
          jwk-set-uri: http://auth.cryptr.eu/t/shark-academy/.well-known # change with provided value
```

### B - Create an Audience Validator

This validator will check if income JWT has proper audience claim.

```java
// security/AudienceValidator.java
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {
  private final String audience;

  AudienceValidator(String audience) {
    this.audience = audience;
  }

  @Override
  public OAuth2TokenValidatorResult validate(Jwt jwt) {
    OAuth2Error error = new OAuth2Error("invalid_token", "The required audience is missing", null);
    if(jwt.getAudience().contains(audience)) {
      return OAuth2TokenValidatorResult.success();
    }
    return OAuth2TokenValidatorResult.failure(error);
  }
}
```

### C - Create a security configuration class to handle requests

This code is based on Spring Security and associated decorators.
This validates JWT and also manages routing authentication.

```java
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
      .cors().and() // /!\ cors should not be present in production mode
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

```

[Next](https://github.com/cryptr-examples/cryptr-spring-security-api-sample/tree/tutorial/4-extract-authentication-data)
