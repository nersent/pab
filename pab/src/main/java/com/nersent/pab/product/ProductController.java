package com.nersent.pab.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nersent.pab.config.ConfigService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
  @Autowired
  private ProductService productService;

  @Autowired
  private ConfigService configService;

  @GetMapping("/all")
  public ResponseEntity<List<ProductData>> getAllProducts() {
    try {
      return ResponseEntity.ok(productService.getAll());
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(null);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductData> getProductById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(productService.getById(id));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(null);
    }
  }
}
