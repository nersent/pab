package com.nersent.pab.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class AdminUIController {
  @GetMapping("/admin/view")
  public String view() {
    return "admin_view";
  }
}
