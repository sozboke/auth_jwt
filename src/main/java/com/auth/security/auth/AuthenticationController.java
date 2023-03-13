package com.auth.security.auth;

import com.auth.security.auth.request.AuthenticationRequest;
import com.auth.security.auth.request.RegisterRequest;
import com.auth.security.auth.response.AuthenticationResponse;
import com.auth.security.auth.response.RegisterResponse;
import com.auth.security.auth.service.AuthenticationService;
import com.auth.security.config.RabbitMQSender;
import com.auth.security.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    private final RabbitMQSender rabbitMQSender;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        var message = service.register(request);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse authenticationResponse = service.authenticate(request);
        rabbitMQSender.send(userService.findUser(authenticationResponse.getUser().getEmail()));
        return ResponseEntity.ok(authenticationResponse);
    }
}
