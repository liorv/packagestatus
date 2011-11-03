package com.lior.packagestatus.server;

import java.util.List;
import org.springframework.stereotype.Service;

import com.lior.packagestatus.client.PackageStatusDO;
import com.lior.packagestatus.client.PackageStatusService;

@Service("packageStatusService")
public class PackageStatusServiceImpl implements PackageStatusService
{
  PackageStatusServiceImpl() {
  }

  public static PackageStatusServiceImpl findEntity(Long id) {
    return new PackageStatusServiceImpl();
  }

  public List<PackageStatusDO> findAllPackageStatus() {
    return PackageStatusLoader.get().load();
  }
}
