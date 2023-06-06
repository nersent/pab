package com.nersent.pab.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.nersent.pab.auth.AuthService;
import com.nersent.pab.user.UserEntity;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductUIController {
  @Autowired
  private AuthService authService;

  @GetMapping("/product")
  public String product() {
    return "product";
  }

  @GetMapping("/basket")
  public Object basket(HttpServletRequest req) {
    Optional<UserEntity> user = authService.getUserFromReq(req);

    if (!user.isPresent()) {
      return new RedirectView("/auth/login");
    }
    return "basket";
  }
}
