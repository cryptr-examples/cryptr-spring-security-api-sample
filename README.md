# Cryptr Spring Security API Sample

## 01 Add Spring Security Dependencies

After creating your Spring project, proceed to add following dependencies to your `pom.xml`` file.

* `spring-boot-starter-security`
* `spring-security-oauth2-resource-server`
* `spring-security-oauth2-jose`
* `spring-security-config`

Your pom file should have this content after that :

```xml
<!-- [...] -->
<dependency>
       <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-oauth2-resource-server</artifactId>
</dependency>
<dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-oauth2-jose</artifactId>
</dependency>
<dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-config</artifactId>
</dependency>
<!-- [...] -->
```


then just run `mvn install` to get them.

[Next](https://github.com/cryptr-examples/cryptr-spring-security-api-sample/tree/tutorial/2-basic-API-Controller)
