package com.auth.security.user.service;

import com.auth.security.user.modal.User;
import com.auth.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUser(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }
}
