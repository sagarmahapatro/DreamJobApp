package  com.dreamjob.auth.controller;

import com.dreamjob.auth.model.User;
import com.dreamjob.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employers")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) { this.service = service; }

    @PostMapping
    public User register(@RequestBody User employer) {
        return service.register(employer);
    }
}
