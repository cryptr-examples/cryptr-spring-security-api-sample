package com.cryptr.test.web;

import org.springframework.http.MediaType;
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
    return "{\"id\": \"1\", \"data\": \"secured-data\"}";
  }
}
