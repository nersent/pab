package com.nersent.pab.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.nersent.pab.auth.AuthService;
import com.nersent.pab.user.UserEntity;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

@Controller
public class AdminUIController {
  @Autowired
  private AuthService authService;

  @GetMapping("/admin/view")
  public Object view(HttpServletRequest req) {
    Optional<UserEntity> user = authService.getUserFromReq(req);

    if (!user.isPresent() || !user.get().isAdmin()) {
      return new RedirectView("/auth/login");
    }

    return "admin_view";
  }

}
