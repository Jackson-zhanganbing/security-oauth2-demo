package com.changan.securitydemo.dao;

import com.changan.securitydemo.entity.SysPermission;

import java.util.List;

public interface PermissionDao {
    List<SysPermission> findAll();

    List<SysPermission> findByAdminUserId(int userId);
}
