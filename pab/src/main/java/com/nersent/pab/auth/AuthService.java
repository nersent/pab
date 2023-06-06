package com.nersent.pab.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nersent.pab.user.UserEntity;
import com.nersent.pab.user.UserRepository;

import jakarta.inject.Inject;

@Service
public class AuthService {
  @Autowired
  private UserRepository userRepository;

  public UserEntity register(String username, String password) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new RuntimeException("Username already taken");
    }

    UserEntity user = new UserEntity();
    user.setUsername(username);
    user.setPassword(password);

    return userRepository.save(user);
  }

  public Optional<UserEntity> authenticate(String username, String password) {
    Optional<UserEntity> user = userRepository.findByUsername(username);
    if (user.isPresent() && password.equals(user.get().getPassword())) {
      return user;
    }
    return Optional.empty();
  }
}
