package com.airboard.api.dao.system;

import com.airboard.api.model.system.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {

    Integer countByPermission(String permission);
}
