package com.example.studentregapp;

import java.util.List;

public class User {
  private   String username;
  private   String password;

  public static List<User> defaultUser(){
      return List.of(
              new User("Herry","003")
      );
  }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
