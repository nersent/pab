package com.nersent.pab.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.nersent.pab.auth.AuthService;
import com.nersent.pab.config.ConfigService;
import com.nersent.pab.user.UserAboutRes;
import com.nersent.pab.user.UserEntity;
import com.nersent.pab.user.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
  @Autowired
  private ConfigService configService;

  @Autowired
  private AuthService authService;

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/make_admin")
  public ResponseEntity<Boolean> about(HttpServletRequest req, HttpServletResponse res) {
    Optional<UserEntity> user = authService.getUserFromReq(req);

    if (!user.isPresent()) {
      return ResponseEntity.status(401).body(false);
    }

    UserEntity userEntity = user.get();

    userEntity.setAdmin(true);

    userEntity = userRepository.save(userEntity);

    return ResponseEntity.ok(true);
  }
}
