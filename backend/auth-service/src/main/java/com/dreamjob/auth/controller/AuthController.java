package  com.dreamjob.auth.controller;

import com.dreamjob.auth.model.User;
import com.dreamjob.auth.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) { this.service = service; }

    @GetMapping("/ping")
    public String ping() {
        return "auth-service is alive!";
    }

    @PostMapping
    public User register(@RequestBody User employer) {
        return service.register(employer);
    }
}
