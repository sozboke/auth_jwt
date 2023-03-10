package com.auth.security.user.service;

import com.auth.security.demo.UpdateUserRequest;
import com.auth.security.token.repository.TokenRepository;
import com.auth.security.user.modal.Role;
import com.auth.security.user.modal.User;
import com.auth.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    public User findUser(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public User updateUser(UpdateUserRequest updateUserRequest) {
        User user = userRepository.findUserByEmail(updateUserRequest.getEmail());
        if (user == null) {
            return null;
        }
        user.setFirstname(updateUserRequest.getFirstname());
        user.setLastname(updateUserRequest.getLastname());
        user.setEmail(updateUserRequest.getEmail());
        user.setRole(updateUserRequest.getRole() == null ? Role.USER : updateUserRequest.getRole());
        user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        userRepository.save(user);

        return user;
    }

    public boolean deleteUser(Integer id) {
        tokenRepository.delete(tokenRepository.findTokenByUserId(id));
        userRepository.delete(userRepository.findUserById(id));
        return userRepository.findUserById(id) == null;
    }

    public String changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return "Success";
    }
}
