package com.nersent.pab.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductUIController {
  @GetMapping("/product")
  public String product() {
    return "product";
  }

  @GetMapping("/basket")
  public String basket() {
    return "basket";
  }
}
