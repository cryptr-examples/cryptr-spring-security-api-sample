# Cryptr Spring Security API Sample

## 02 Create basic API Controller

To begin, let's create a simple API Controller with two routes:

 - first one (`api/v1`/`) will be a public endpoint
 - second one (`api/v1/secured-resource`) will be a secured one requiring authentication to return the value

> For current Sample we do not configure CORS, but in production you have to configure them

Here is the content of your controller file

```java
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
```

[Next](https://github.com/cryptr-examples/cryptr-spring-security-api-sample/tree/tutorial/3-configure-security)
