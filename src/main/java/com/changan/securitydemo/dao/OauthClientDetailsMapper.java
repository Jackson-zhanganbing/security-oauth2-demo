package com.changan.securitydemo.dao;

import com.changan.securitydemo.entity.OauthClientDetails;
import org.apache.ibatis.annotations.Param;

public interface OauthClientDetailsMapper {
    int insert(OauthClientDetails record);

    int insertSelective(OauthClientDetails record);

    OauthClientDetails selectOne(@Param("clientId") String clientId);
}