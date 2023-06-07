package com.nersent.pab.user.products;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_user_links")
public class UserProductLinkEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  private Long userId;

  private Long productId;

  public UserProductLinkEntity() {
  }

  public UserProductLinkEntity(Long userId, Long productId) {
    this.userId = userId;
    this.productId = productId;
  }

  public Long getId() {
    return this.id;
  }

  public Long getUserId() {
    return this.userId;
  }

  public Long getProductId() {
    return this.productId;
  }
}
