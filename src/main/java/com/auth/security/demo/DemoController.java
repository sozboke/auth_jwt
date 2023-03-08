package com.auth.security.demo;

import com.auth.security.user.modal.User;
import com.auth.security.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
@RequiredArgsConstructor
public class DemoController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<User> sayHello() {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUser(authUser.getName());
        return ResponseEntity.ok(user);
    }
}
