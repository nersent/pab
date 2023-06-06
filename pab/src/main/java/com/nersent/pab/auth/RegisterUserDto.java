package com.nersent.pab.auth;

public class RegisterUserDto {
  private String username;
  private String password;

  public RegisterUserDto() {
  }

  public RegisterUserDto(String username, String password) {
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
