package com.nersent.pab.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nersent.pab.user.UserEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UserEntity user) {
    Optional<UserEntity> authenticatedUser = authService.authenticate(user.getUsername(), user.getPassword());
    if (authenticatedUser.isPresent()) {
      return ResponseEntity.ok("Authenticated");
    } else {
      return ResponseEntity.status(401).body("Invalid username or password");
    }
  }
}
