package com.nersent.pab.admin;

import java.util.ArrayList;
import java.util.List;
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
import com.nersent.pab.product.ProductData;
import com.nersent.pab.product.ProductService;
import com.nersent.pab.user.UserAboutRes;
import com.nersent.pab.user.UserEntity;
import com.nersent.pab.user.UserRepository;
import com.nersent.pab.user.products.UserProductLinkEntity;
import com.nersent.pab.user.products.UserProductLinkService;

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

  @Autowired
  private UserProductLinkService userProductLinkService;

  @Autowired
  private ProductService productService;

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

  @GetMapping("/list")
  public ResponseEntity<List<AdminProductDto>> list(HttpServletRequest req, HttpServletResponse res) {
    try {
      Optional<UserEntity> user = authService.getUserFromReq(req);

      if (!user.isPresent() || !user.get().isAdmin()) {
        return ResponseEntity.status(401).body(null);
      }

      List<UserProductLinkEntity> links = userProductLinkService.getAll();

      List<ProductData> products = productService.getAll();
      List<AdminProductDto> adminProducts = new ArrayList<>();

      for (ProductData product : products) {
        for (UserProductLinkEntity link : links) {
          if (link.getProductId() == product.id) {
            Optional<UserEntity> linkUser = userRepository.findById(link.getUserId());
            if (!linkUser.isPresent()) {
              continue;
            }
            adminProducts.add(new AdminProductDto(user.get().getUsername(), product));
          }
        }
      }

      return ResponseEntity.ok(adminProducts);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(null);
    }
  }
}
