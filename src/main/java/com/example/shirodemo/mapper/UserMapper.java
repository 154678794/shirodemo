package com.example.shirodemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shirodemo.entity.GeneralUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * TODO
 *
 * @author admin
 * @version 1.0
 * @date 2021/2/23 18:04
 */
@Mapper
public interface UserMapper extends BaseMapper<GeneralUserEntity> {
    @Select("select * from t_general_user where user_name = #{username}")
    GeneralUserEntity queryOne(String username);
}
