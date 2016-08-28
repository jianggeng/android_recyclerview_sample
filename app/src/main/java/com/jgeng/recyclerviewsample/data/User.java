package com.jgeng.recyclerviewsample.data;

import java.util.Date;

/**
 * Created by jgeng on 8/16/16.
 */

public class User {
  private String userId;
  private String name;
  private String address;
  private Date dateOfBirth;

  public User() {
  }

  public User(String userId, String name, String address) {
    this.userId = userId;
    this.name = name;
    this.address = address;
    this.dateOfBirth = new Date();
  }

  public String getId() {
    return userId;
  }
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
}