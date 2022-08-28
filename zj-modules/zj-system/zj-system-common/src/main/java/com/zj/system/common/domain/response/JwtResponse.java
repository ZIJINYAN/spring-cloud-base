package com.zj.system.common.domain.response;

import lombok.Data;

/**
 * @author zj
 * @create 2022-08-28 14:41
 */
@Data
public class JwtResponse {
    private String token;
    private String expired;
}
