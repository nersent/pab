package com.nersent.pab.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nersent.pab.auth.AuthService;
import com.nersent.pab.config.ConfigService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  private AuthService authService;

  @Autowired
  private ConfigService configService;

  @PostMapping("/about")
  public ResponseEntity<UserAboutRes> about(HttpServletRequest req, HttpServletResponse res) {
    Optional<UserEntity> user = authService.getUserFromReq(req);

    if (!user.isPresent()) {
      return ResponseEntity.status(401).body(null);
    }

    UserAboutRes about = new UserAboutRes(user.get().getUsername(), user.get().getId());

    return ResponseEntity.ok(about);
  }
}
