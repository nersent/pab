package com.nersent.pab.auth;

public class LoginUserDto {
  private String username;
  private String password;

  public LoginUserDto() {
  }

  public LoginUserDto(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }
}
