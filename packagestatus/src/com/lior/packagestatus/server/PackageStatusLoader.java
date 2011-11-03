package com.lior.packagestatus.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import com.lior.packagestatus.client.PackageStatusDO;

import context.AppContext;

public class PackageStatusLoader
{
  public static PackageStatusLoader get() {
    ApplicationContext ctx = AppContext.getApplicationContext();
    return (PackageStatusLoader) ctx.getBean("packageStatusLoader");
  }

  public String url;

  public List<PackageStatusDO> load() {

    List<PackageStatusDO> retval = new LinkedList<PackageStatusDO>();
    try {
      URL u = new URL(url);
      BufferedReader br =
          new BufferedReader(new InputStreamReader(u.openStream()));
      for (String line = br.readLine(); line != null; line = br.readLine()) {
        String[] tokens = line.split(",");
        String packageNum = tokens[0];
        String status = tokens[1];
        String cause = tokens[2];
        retval.add(new PackageStatusDO(packageNum, status, cause));
      }
    }
    catch (IOException e) {
      return retval;
    }
    return retval;
    /*
     * retval.add(new PackageStatusDO("0103", "RUNNING", "/log1??"));
     * retval.add(new PackageStatusDO("0102", "PASS", "/log1")); retval.add(new
     * PackageStatusDO("0101", "FAIL", "/log2")); retval.add(new
     * PackageStatusDO("0100", "PASS", "/log3"));
     * 
     * return retval;
     */
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
