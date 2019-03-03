package com.changan.securitydemo.service;

import com.changan.securitydemo.common.Msg;

public interface UserService {

    Msg findByUsername(String userName);
}
