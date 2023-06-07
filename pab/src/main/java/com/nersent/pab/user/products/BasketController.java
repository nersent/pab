package com.nersent.pab.user.products;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nersent.pab.auth.AuthService;
import com.nersent.pab.config.ConfigService;
import com.nersent.pab.product.ProductData;
import com.nersent.pab.product.ProductService;
import com.nersent.pab.user.UserEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/basket")
public class BasketController {
  @Autowired
  private ProductService productService;

  @Autowired
  private ConfigService configService;

  @Autowired
  private UserProductLinkService userProductLinkService;

  @Autowired
  private AuthService authService;

  @GetMapping("/")
  public ResponseEntity<List<ProductData>> getBasketForCurrentUser(HttpServletRequest req, HttpServletResponse res) {
    try {
      Optional<UserEntity> user = authService.getUserFromReq(req);

      if (!user.isPresent()) {
        return ResponseEntity.status(401).body(null);
      }

      Optional<List<UserProductLinkEntity>> links = userProductLinkService.getListForUser(user.get().getId());
      if (!links.isPresent()) {
        return ResponseEntity.ok(new ArrayList<>());
      }

      List<ProductData> products = productService.getAll();
      List<ProductData> basket = new ArrayList<>();

      for (ProductData product : products) {
        for (UserProductLinkEntity link : links.get()) {
          if (link.getProductId() == product.id) {
            basket.add(product);
          }
        }
      }

      return ResponseEntity.ok(basket);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(null);
    }
  }

  @PostMapping("/add/{id}")
  public ResponseEntity<ProductData> add(@PathVariable Long id, HttpServletRequest req) {
    try {
      ProductData product = productService.getById(id);
      if (product == null) {
        return ResponseEntity.badRequest().body(null);
      }

      Optional<UserEntity> user = authService.getUserFromReq(req);
      if (!user.isPresent()) {
        return ResponseEntity.status(401).body(null);
      }

      Optional<UserProductLinkEntity> link = userProductLinkService.get(user.get().getId(), id);
      if (link.isPresent()) {
        return ResponseEntity.badRequest().body(null);
      }

      UserProductLinkEntity newLink = new UserProductLinkEntity(user.get().getId(), id);
      userProductLinkService.save(newLink);

      return ResponseEntity.ok(product);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(null);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> delete(@PathVariable Long id, HttpServletRequest req) {
    try {
      ProductData product = productService.getById(id);
      if (product == null) {
        return ResponseEntity.badRequest().body(null);
      }

      Optional<UserEntity> user = authService.getUserFromReq(req);
      if (!user.isPresent()) {
        return ResponseEntity.status(401).body(null);
      }

      Optional<UserProductLinkEntity> link = userProductLinkService.get(user.get().getId(), id);
      if (!link.isPresent()) {
        return ResponseEntity.badRequest().body(null);
      }

      userProductLinkService.delete(link.get());

      return ResponseEntity.ok(true);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(null);
    }
  }
}