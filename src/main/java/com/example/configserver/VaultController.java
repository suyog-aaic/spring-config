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