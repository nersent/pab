package com.nersent.pab.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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
      Cookie jwtCookie = authService.createJWTCookie(jwt);
      response.addCookie(jwtCookie);
      return ResponseEntity.ok(jwt);
    } else {
      return ResponseEntity.status(401).body("Invalid username or password");
    }
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterUserDto data, HttpServletResponse response) {
    try {
      UserEntity user = authService.register(data.getUsername(), data.getPassword());
      String jwt = authService.createJWT(user);
      Cookie jwtCookie = authService.createJWTCookie(jwt);
      response.addCookie(jwtCookie);
      return ResponseEntity.ok("Registered");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/logout")
  public RedirectView logout(HttpServletResponse response) {
    Cookie jwtCookie = authService.createJWTCookie("");
    jwtCookie.setMaxAge(0);
    response.addCookie(jwtCookie);
    return new RedirectView("/");
  }
}
