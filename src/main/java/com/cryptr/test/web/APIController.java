package com.cryptr.test.web;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
// For simplicity of this sample, allow all origins. Real applications should
// configure CORS for their use case.
@CrossOrigin(origins = "*")
public class APIController {
  
  @GetMapping(value = "/")
  public String publicEndpoint() {
    return "Resource Server API";
  }

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
}
