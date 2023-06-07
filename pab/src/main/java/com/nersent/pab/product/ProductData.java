package com.nersent.pab.product;

public class ProductData {
  public Long id;

  public String name;

  public String description;

  public double price;

  public String image;

  public ProductData() {
  }

  public ProductData(Long id, String name, String description, double price, String image) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.image = image;
  }
}
