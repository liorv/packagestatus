package com.lior.packagestatus.server;

public class PackageData
{
  public PackageData() {}

  public PackageData(String packageId, String state, String message) {
    id = packageId;
    this.state = state;
    this.message = message;
  }

  public String id;
  public String state;
  public String message;
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
