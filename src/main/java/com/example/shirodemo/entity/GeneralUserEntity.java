package com.example.shirodemo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("t_general_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralUserEntity {
  @TableId(type = IdType.AUTO)
  private long userId;
  @TableField("user_name")
  private String userName;
  @TableField("real_name")
  private String realName;
  @TableField("password")
  private String password;
  @TableField("phone_num")
  private String phoneNum;
  @TableField("regist_time")
  private Date registTime;
  @TableField("login_time")
  private Date loginTime;
  @TableField("sex")
  private long sex;
  @TableField("birth")
  private Date birth;
  @TableField("money")
  private double money;
  @TableField("rights")
  private long rights;
}
