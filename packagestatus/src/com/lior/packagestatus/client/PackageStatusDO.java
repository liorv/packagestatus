package com.lior.packagestatus.client;

import java.io.Serializable;

public class PackageStatusDO implements Serializable
{
  private static final long serialVersionUID = -1447146928403813249L;

  public PackageStatusDO() {
    id = "UNDEFINED";
    state = "UNDEFINED";
    message = "UNDEFINED";
  }
  
  public PackageStatusDO(String id, String state, String message) {
    this.id = id;
    this.state = state;
    this.message = message;
  }

  public String id;
  public String state;
  public String message;

  public static PackageStatusDO findEntity(Long id) {
    return new PackageStatusDO();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
