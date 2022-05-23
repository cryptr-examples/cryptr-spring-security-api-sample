# Cryptr Spring Security API Sample

## 5 - Use Scope Authorities

Cryptr generates Access And ID JWT including the authorized scopes for the current user for the current session.

That means that you can check if this token is allowed to access or not to a specific resource.

In Spring Security this is related to `Authorities`, and this is usable in the `SecurityConfig` file to filter directly from there instead of check inside each Mapping function.

Let's set two rules:

- The `/api/v1/resource-for-profile` route is allowed to users that have `profile` in their JWT scope
- The `/api/v1/resource-including-scopes` route is allowed to users that have either `profile` or `openid` in their JWT scope.


First, let's update SecurityConfig file as below :

```java
.mvcMatchers("/api/v1/secured-resource").authenticated()
.mvcMatchers("/api/v1/resource-for-profile").hasAuthority("SCOPE_profile") // chose scope you want
.mvcMatchers("/api/v1/resource-including-scopes").hasAnyAuthority("SCOPE_profile", "SCOPE_openid") // chose scopes you want
.and().oauth2ResourceServer().jwt();
```

For more see [Spring documentation](https://docs.spring.io/spring-security/site/docs/4.2.x/reference/html/el-access.html#el-common-built-in).

Finally let's update API Controller to create routes that will match the above configuration:


```java
// APIControler.java
  @GetMapping(value = "/resource-for-profile")
  public String resourceForScope(){
    // this request requires a scope you define in security config
    return "{\"id\": \"1\", \"data\": \"secured-data\"}";
  }

  @GetMapping(value = "/resource-including-scopes")
  public String resourceForAnyScope(){
    // This request requires a token with a scope contained in list defined in security config
    return "{\"id\": \"1\", \"data\": \"secured-data\"}";
  }
```

That's all of Cryptr's Spring Security implementation.

As a reminder we covered the following steps:

1. Add required Spring Security dependencies
2. Create a basic API Controller that receives modifications to handle basic Crpytr Check
3. Spring Security  configuration for Cryptr
4. Extract Authentication params for narrow checks
5. Define some Spring security scope filtering using Spring security configuration.