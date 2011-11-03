package com.lior.packagestatus.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PackageStatusServiceAsync
{
  void findAllPackageStatus(AsyncCallback<List<PackageStatusDO>> callback);
}
