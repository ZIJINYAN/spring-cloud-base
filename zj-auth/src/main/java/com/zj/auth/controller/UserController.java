package com.zj.auth.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zj.common.core.constants.JwtConstants;
import com.zj.common.core.constants.TokenConstants;
import com.zj.common.core.domain.Result;
import com.zj.common.core.exception.BizException;
import com.zj.common.core.utils.JwtUtils;
import com.zj.common.core.utils.StringUtils;
import com.zj.system.common.domain.UserEntity;
import com.zj.system.common.response.JwtResponse;
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
    public Result<JwtResponse> userLogin(@RequestBody UserEntity userLogin){
        UserEntity user = userServiceRemote.userLogin(userLogin).getData();
        if(user == null){
            throw new BizException(500,"用户名或者密码错误!");
        }
        String userKey = IdUtil.fastSimpleUUID();
        Map<String,Object> map = new HashMap<>();
        map.put(JwtConstants.DETAILS_USER_ID, user.getUserId());
        map.put(JwtConstants.DETAILS_USERNAME, user.getUsername());
        map.put(JwtConstants.USER_KEY, userKey);
        String token = JwtUtils.createToken(map);
        redisTemplate.opsForValue().set(TokenConstants.LOGIN_TOKEN_KEY+userKey, JSON.toJSONString(user));
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(token);
        jwtResponse.setExpired("");
        return Result.success(jwtResponse);
    }

    @GetMapping("/info")
    public Result<UserEntity> userInfo(@RequestParam("token")String token){
        if(StringUtils.isEmpty(token)){
            throw new BizException(500,"token获取失败!");
        }
        String userKey = JwtUtils.getUserKey(token);
        UserEntity user = JSONObject.parseObject(
                redisTemplate.opsForValue().get(TokenConstants.LOGIN_TOKEN_KEY + userKey),
                UserEntity.class
        );
        return Result.success(user,"成功");
    }
}
