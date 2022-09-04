package com.zj.system.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zj
 * @create 2022-08-28 14:16
 */
@Data
@TableName("system_user")
public class UserEntity {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户姓名
     */
    private String realName;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 0-未关注 1-关注
     */
    private String subscription;

}
