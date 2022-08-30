package com.zj.auth.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zj.common.core.constants.JwtConstants;
import com.zj.common.core.constants.TokenConstants;
import com.zj.common.core.domain.Result;
import com.zj.common.core.utils.JwtUtils;
import com.zj.common.log.annotation.Log;
import com.zj.system.common.domain.UserEntity;
import com.zj.system.common.domain.response.JwtResponse;
import com.zj.system.remote.UserServiceRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zj
 * @create 2022-08-28 14:36
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceRemote userServiceRemote;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public Result<JwtResponse> login(@RequestBody UserEntity userLogin){
        UserEntity user = userServiceRemote.login(userLogin).getData();
        String userKey = IdUtil.fastSimpleUUID();
        redisTemplate.opsForValue().set(TokenConstants.LOGIN_TOKEN_KEY+userKey, JSON.toJSONString(user));
        Map<String,Object> map = new HashMap<>();
        map.put(JwtConstants.DETAILS_USER_ID,user.getUserId());
        map.put(JwtConstants.USER_KEY, userKey);
        map.put(JwtConstants.DETAILS_USERNAME, user.getUsername());
        String token = JwtUtils.createToken(map);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(token);
        return Result.success(jwtResponse);
    }

    @GetMapping("/info")
    @Log(title = "获取用户信息")
    public Result<UserEntity> info(@RequestParam("token")String token){
        String userKey = JwtUtils.getUserKey(token);
        UserEntity user = JSONObject.parseObject(
                redisTemplate.opsForValue().get(TokenConstants.LOGIN_TOKEN_KEY + userKey),
                UserEntity.class
        );
        return Result.success(user);
    }
}
