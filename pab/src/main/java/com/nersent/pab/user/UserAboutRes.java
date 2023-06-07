package com.nersent.pab.user;

public class UserAboutRes {
  private String username;
  private Long id;

  private boolean isAdmin;

  public UserAboutRes(String username, Long id, boolean isAdmin) {
    this.username = username;
    this.id = id;
    this.isAdmin = isAdmin;
  }

  public String getUsername() {
    return this.username;
  }

  public Long getId() {
    return this.id;
  }

  public boolean isAdmin() {
    return this.isAdmin;
  }
}
