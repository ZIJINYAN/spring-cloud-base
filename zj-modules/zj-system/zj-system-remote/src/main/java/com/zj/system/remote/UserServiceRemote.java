package com.zj.system.remote;

import com.zj.common.core.domain.Result;
import com.zj.system.common.domain.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zj
 * @create 2022-08-28 14:37
 */
@FeignClient("ZJ-system")
public interface UserServiceRemote {
    @PostMapping("/user/login")
    public Result<UserEntity> userLogin(@RequestBody UserEntity userLogin);
}
