package com.zj.gateway.filter;

import com.zj.common.core.constants.JwtConstants;
import com.zj.common.core.constants.TokenConstants;
import com.zj.common.core.utils.JwtUtils;
import com.zj.common.core.utils.StringUtils;
import com.zj.gateway.config.IgnoreWhiteConfig;
import com.zj.gateway.utils.GatewayUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class AuthFilter implements GlobalFilter, Ordered {
    /**
     * redis操作
     */
    private final StringRedisTemplate redisTemplate;
    /**
     * 白名单
     */
    private final IgnoreWhiteConfig ignoreWhite;
    public AuthFilter(StringRedisTemplate redisTemplate, IgnoreWhiteConfig ignoreWhite) {
        this.redisTemplate = redisTemplate;
        this.ignoreWhite = ignoreWhite;
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 请求作用域
        ServerHttpRequest request = exchange.getRequest();
        // 请求头
        HttpHeaders headers = request.getHeaders();
        // 请求方式
        HttpMethod method = request.getMethod();
        // header操作对象
        ServerHttpRequest.Builder mutate = request.mutate();
        String uri = request.getURI().getPath();
        log.info("请求日志：uri:[{}] , 请求方式:[{}]", uri, method);
        // 跳过不需要验证的路径
        if (StringUtils.matches(uri, ignoreWhite.getWhites())) {
            return chain.filter(exchange);
        }
        String token = headers.getFirst(TokenConstants.TOKEN);
        if (StringUtils.isEmpty(token)) {
            return GatewayUtils.errorResponse(exchange, "令牌不能为空");
        }
        Claims claims = JwtUtils.parseToken(token);
        if (claims == null) {
            return GatewayUtils.errorResponse(exchange, "令牌已过期或验证不正确！");
        }
        String userKey = JwtUtils.getUserKey(claims);
        boolean isLogin = redisTemplate.hasKey(TokenConstants.LOGIN_TOKEN_KEY+userKey);
        if (!isLogin) {
            return GatewayUtils.errorResponse(exchange, "登录状态已过期");
        }
        String userid = JwtUtils.getUserId(claims);
        String username = JwtUtils.getUserName(claims);
        // 设置用户信息到请求
        GatewayUtils.addHeader(mutate, JwtConstants.USER_KEY, userKey);
        GatewayUtils.addHeader(mutate, JwtConstants.DETAILS_USER_ID, userid);
        GatewayUtils.addHeader(mutate, JwtConstants.DETAILS_USERNAME, username);
        // 内部请求来源参数清除
        GatewayUtils.removeHeader(mutate, TokenConstants.TOKEN);
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
    @Override
    public int getOrder() {
        return 0;
    }
}
