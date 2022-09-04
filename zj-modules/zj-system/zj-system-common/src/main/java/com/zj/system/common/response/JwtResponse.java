package com.zj.system.common.response;

import lombok.Data;

/**
 * @author zj
 * @create 2022-09-02 16:14
 */
@Data
public class JwtResponse {
    private String token;
    private String expired;
}
