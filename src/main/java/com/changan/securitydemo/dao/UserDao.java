package com.changan.securitydemo.dao;

import com.changan.securitydemo.entity.SysUser;

public interface UserDao {
    SysUser findByUserName(String username);
}
