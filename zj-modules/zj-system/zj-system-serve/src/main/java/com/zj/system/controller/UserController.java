package com.zj.system.controller;

import com.zj.common.core.domain.Result;
import com.zj.system.common.domain.UserEntity;
import com.zj.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zj
 * @create 2022-08-28 14:15
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public Result<UserEntity> userLogin(@RequestBody UserEntity userLogin){
        return Result.success(userService.userLogin(userLogin),"成功");
    }

    @PostMapping("/subscription")
    public Result<?> subscription(){
        userService.subscription();
        return Result.success(null,"成功");
    }

}
