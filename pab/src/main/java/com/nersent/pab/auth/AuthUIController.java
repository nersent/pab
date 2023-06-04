package com.nersent.pab.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthUIController {
  @GetMapping("/auth/login")
  public String login() {
    return "auth/login";
  }
}
