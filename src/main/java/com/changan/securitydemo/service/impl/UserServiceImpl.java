package com.changan.securitydemo.service.impl;

import com.changan.securitydemo.common.Msg;
import com.changan.securitydemo.dao.UserDao;
import com.changan.securitydemo.entity.SysUser;
import com.changan.securitydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public Msg findByUsername(String userName) {

        SysUser user = userDao.findByUserName(userName);
        Msg msg = new Msg("查询成功",user,"查询成功！！！！");
        return msg;
    }

}
