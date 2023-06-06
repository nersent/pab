package com.nersent.pab.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nersent.pab.config.ConfigService;
import com.nersent.pab.user.UserEntity;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private AuthService authService;

  @Autowired
  private ConfigService configService;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginUserDto user, HttpServletResponse response) {
    Optional<UserEntity> authenticatedUser = authService.authenticate(user.getUsername(), user.getPassword());
    if (authenticatedUser.isPresent()) {
      String jwt = authService.createJWT(authenticatedUser.get());

      Cookie jwtCookie = new Cookie(configService.getJwtCookieName(), jwt);
      jwtCookie.setMaxAge(configService.getJwtExpirationTime());
      jwtCookie.setPath("/");
      jwtCookie.setHttpOnly(false);
      response.addCookie(jwtCookie);

      return ResponseEntity.ok(jwt);
    } else {
      return ResponseEntity.status(401).body("Invalid username or password");
    }
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterUserDto data) {
    try {
      authService.register(data.getUsername(), data.getPassword());
      return ResponseEntity.ok("Registered");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
