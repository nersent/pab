package com.nersent.pab.user;

public class UserAboutRes {
  private String username;
  private Long id;

  public UserAboutRes(String username, Long id) {
    this.username = username;
    this.id = id;
  }

  public String getUsername() {
    return this.username;
  }

  public Long getId() {
    return this.id;
  }
}
