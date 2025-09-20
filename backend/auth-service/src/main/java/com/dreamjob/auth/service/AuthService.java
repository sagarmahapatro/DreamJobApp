package com.dreamjob.auth.service;

import com.dreamjob.auth.model.User;
import com.dreamjob.auth.repository.AuthRepository;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final AuthRepository repo;

    public AuthService(AuthRepository repo) {
        this.repo = repo;
    }

    public User register(User e) {
        User saved = repo.save(e);
        return saved;
    }
}
