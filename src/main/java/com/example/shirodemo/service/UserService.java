package com.example.shirodemo.service;

import com.example.shirodemo.entity.GeneralUserEntity;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * TODO
 *
 * @author admin
 * @version 1.0
 * @date 2021/2/23 18:03
 */
public interface UserService {
    GeneralUserEntity queryUser(UsernamePasswordToken userToken);
}
