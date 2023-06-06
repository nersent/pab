package com.nersent.pab.config;

import org.springframework.stereotype.Service;

@Service
public class ConfigService {
  public String getJwtToken() {
    return "JWT_SECRET_KEY";
  }

  public int getJwtExpirationTime() {
    return 24 * 60 * 60 * 1000;
  }

  public String getJwtCookieName() {
    return "jwt";
  }
}
