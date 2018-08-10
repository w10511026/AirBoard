package com.airboard.core.dao.system;

import com.airboard.core.model.system.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {

    Integer countByPermission(String permission);
}
