package com.zhr.service.impl;

import com.zhr.mapper.UserInfoMapper;
import com.zhr.model.user.UserInfo;
import com.zhr.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getById(Long id) {
        return userInfoMapper.selectById(id);

    }
}
