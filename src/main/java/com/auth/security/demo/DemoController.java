package com.auth.security.demo;

import com.auth.security.user.modal.User;
import com.auth.security.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo-controller")
@RequiredArgsConstructor
public class DemoController {
    private final UserService userService;

    @GetMapping
    @RequestMapping("/api/v1/demo-controller/getUser")
    public ResponseEntity<User> sayHello() {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUser(authUser.getName());
        return ResponseEntity.ok(user);
    }


    @PostMapping
    @RequestMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUser(updateUserRequest));
    }

    @DeleteMapping
    @RequestMapping("/deleteUser/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
