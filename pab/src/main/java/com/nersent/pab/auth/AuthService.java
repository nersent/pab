package com.nersent.pab.auth;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nersent.pab.config.ConfigService;
import com.nersent.pab.user.UserEntity;
import com.nersent.pab.user.UserRepository;

import jakarta.inject.Inject;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthService {
  @Autowired
  private ConfigService configService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserEntity register(String username, String password) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new RuntimeException("Username already taken");
    }

    UserEntity user = new UserEntity();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));

    return userRepository.save(user);
  }

  public Optional<UserEntity> authenticate(String username, String password) {
    Optional<UserEntity> user = userRepository.findByUsername(username);
    if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
      return user;
    }
    return Optional.empty();
  }

  public String createJWT(UserEntity user) {
    Algorithm algorithm = Algorithm.HMAC256(configService.getJwtToken());
    return JWT.create()
        .withIssuer("auth0")
        .withClaim("userId", user.getId())
        .withExpiresAt(new Date(System.currentTimeMillis() + configService.getJwtExpirationTime()))
        .sign(algorithm);
  }

  public Cookie createJWTCookie(String jwt) {
    Cookie jwtCookie = new Cookie(configService.getJwtCookieName(), jwt);
    jwtCookie.setMaxAge(configService.getJwtExpirationTime());
    jwtCookie.setPath("/");
    jwtCookie.setHttpOnly(false);

    return jwtCookie;
  }

  public Optional<String> getJwtTokenFromReq(HttpServletRequest req) {
    Cookie[] cookies = req.getCookies();

    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(configService.getJwtCookieName())) {
          return Optional.of(cookie.getValue());
        }
      }
    }

    if (req.getHeader("Authorization") != null) {
      var value = req.getHeader("Authorization");

      if (value.startsWith("Bearer ")) {
        return Optional.of(value.substring(7));
      }

      return Optional.of(value);
    }

    return Optional.empty();
  }

  public Optional<UserEntity> getUserFromReq(HttpServletRequest req) {
    Optional<String> jwt = getJwtTokenFromReq(req);

    if (!jwt.isPresent()) {
      return Optional.empty();
    }

    Algorithm algorithm = Algorithm.HMAC256(configService.getJwtToken());
    var verifier = JWT.require(algorithm).withIssuer("auth0").build();
    var decodedJWT = verifier.verify(jwt.get());

    var userId = decodedJWT.getClaim("userId").asLong();
    return userRepository.findById(userId);
  }
}
