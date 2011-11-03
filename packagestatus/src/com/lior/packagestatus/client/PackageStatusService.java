package com.lior.packagestatus.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Client side interface for the greeting service.
 */
@RemoteServiceRelativePath("springGwtServices/packageStatusService")
public interface PackageStatusService extends RemoteService
{
  List<PackageStatusDO> findAllPackageStatus();
}
