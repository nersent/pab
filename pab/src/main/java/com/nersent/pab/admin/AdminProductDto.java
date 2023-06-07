package com.nersent.pab.admin;

import com.nersent.pab.product.ProductData;

public class AdminProductDto {
  public String username;

  public ProductData product;

  public AdminProductDto(String username, ProductData product) {
    this.username = username;
    this.product = product;
  }
}
