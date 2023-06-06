package com.nersent.pab.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class AuthUIController {
  @GetMapping("/auth/register")
  public String register() {
    return "register";
  }

  @GetMapping("/auth/login")
  public String login() {
    return "login";
  }
}
