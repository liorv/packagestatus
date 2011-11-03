package com.lior.packagestatus.shared;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;

public class DynamicImageResource implements ImageResource
{
  public DynamicImageResource(String u) {
    height = 20;
    width = 20;
    url = u;
  }
  
  public DynamicImageResource(int h, int w, String u) {
    height = h;
    width = w;
    url = u;
  }

  private int height = 0;
  private int width = 0;
  private final String url;

  @Override
  public String getName() {
    return url;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getLeft() {
    return 0;
  }

  @Override
  public SafeUri getSafeUri() {
    return UriUtils.fromSafeConstant(getURL());
  }

  @Override
  public int getTop() {
    return 0;
  }

  @Override
  public String getURL() {
    return url;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public boolean isAnimated() {
    return false;
  }

}
