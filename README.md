# Cryptr Spring Security API Sample

## 04 Retrieve Authentication Parameters

As this implementation is based on Spring Security, you keep all Spring security tools to set proper behavior depending on current authentication.

Spring Security has `Authentication` in it's core package, this model can be used in your controller to define how to respond properly.

Here is the code line to retrieve the current authentication:

```java
Authentication auth = SecurityContextHolder.getContex().getAuthentication();
```

See below example that only prints out authentication data, if you want more see [Spring Security Authentication Doc](https://docs.spring.io/spring-security/site/docs/4.2.11.RELEASE/apidocs/index.html?org/springframework/security/core/Authentication.html)

```java
//APIController.java
@GetMapping(value = "/secured-resource")
  public String privateEndpoint(){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // auth hosts data from token provided in request
    // auth.getName() provides the reference of end-user ID in our DB
    // auth.getAuthorities() list all scopes included in the token
    // they are all Prefixed with SCOPE_ example : "SCOPE_read:messages"
    // use these methods for custom actions or precise restrictions
    System.out.println(auth.getName());
    System.out.println(auth.getAuthorities());
    return "{\"id\": \"1\", \"data\": \"secured-data\"}";
  }
```

Depending on your proper needs, you can either respond successfully or deny the request.


[Next](https://github.com/cryptr-examples/cryptr-spring-security-api-sample/tree/tutorial/5-use-scope-authorities)
