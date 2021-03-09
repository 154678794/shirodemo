package com.example.shirodemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shirodemo.entity.GeneralUserEntity;
import com.example.shirodemo.mapper.UserMapper;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author admin
 * @version 1.0
 * @date 2021/2/23 18:26
 */
@Service
public class UserImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public GeneralUserEntity queryUser(UsernamePasswordToken userToken) {
//        GeneralUserEntity user = userMapper.queryOne(userToken.getUsername());
        GeneralUserEntity user = userMapper.selectOne(new QueryWrapper<GeneralUserEntity>().eq("user_name", userToken.getUsername()));
        return user;
    }
}
