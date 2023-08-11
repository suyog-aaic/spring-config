package com.example.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
/*
public class ConfigserverApplication {

        public static void main(String[] args) {
                SpringApplication.run(ConfigserverApplication.class, args);
        }

}
*/

public class ConfigServer {
  public static void main(String[] args) {
    SpringApplication.run(ConfigServer.class, args);
  }
}

@RestController
@RequestMapping("/api/vault")
public class VaultController {

    private final VaultTemplate vaultTemplate;

    public VaultController(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
    }

    @PostMapping("/secrets")
    public ResponseEntity<String> addSecret(@RequestBody Map<String, String> secret) {
        vaultTemplate.write("secret/data/myapp", secret);
        return ResponseEntity.ok("Secret added successfully");
    }
}