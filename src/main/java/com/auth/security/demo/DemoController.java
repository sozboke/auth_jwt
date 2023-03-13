package com.auth.security.demo;

import com.auth.security.user.modal.Role;
import com.auth.security.user.modal.User;
import com.auth.security.user.service.UserService;
import jakarta.validation.Valid;
import org.json.JSONException;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/demo-controller")
@RequiredArgsConstructor
public class DemoController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    @RequestMapping("/api/v1/demo-controller/getUser")
    public ResponseEntity<User> sayHello() {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUser(authUser.getName());
        return ResponseEntity.ok(user);
    }


    @PostMapping
    @RequestMapping("/updateUser")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUser(updateUserRequest));
    }

    @DeleteMapping
    @RequestMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) throws JSONException {
        JSONObject returnMessage = new JSONObject();
        User user = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (!user.getRole().name().equalsIgnoreCase("Admin")) {
            returnMessage.put("code", 301);
            returnMessage.put("message", "User is not admin");
            returnMessage.put("returnMessage", false);
            return ResponseEntity.status(301).body(returnMessage.toString());
        }
        return ResponseEntity.ok(returnMessage.toString());
    }

    @PostMapping
    @RequestMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(changePasswordRequest.getEmail(), changePasswordRequest.getOldPassword())
            );
            if (authenticate.isAuthenticated()) {
                User user = userService.findUser(changePasswordRequest.getEmail());
                userService.changePassword(user, changePasswordRequest.getNewPassword());
                return ResponseEntity.ok("Success");
            }
        } catch (Exception e) {
            return ResponseEntity.ok("Credentials are wrong");
        }
        return ResponseEntity.ok("Credentials are wrong");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
