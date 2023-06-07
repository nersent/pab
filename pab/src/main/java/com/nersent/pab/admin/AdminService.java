package com.nersent.pab.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.nersent.pab.config.ConfigService;
import com.nersent.pab.user.UserEntity;
import com.nersent.pab.user.UserRepository;

public class AdminService {
  @Autowired
  private ConfigService configService;

  @Autowired
  private UserRepository userRepository;
}
