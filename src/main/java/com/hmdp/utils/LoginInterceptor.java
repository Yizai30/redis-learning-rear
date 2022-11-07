package com.hmdp.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.hmdp.dto.UserDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 登录拦截器
 *
 * @author yizai
 * @since 2022/11/7 17:01
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * Controller 执行之前的预处理
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 判断是否需要拦截（ThreadLocal 中是否有用户）
        if (UserHolder.getUser() == null) {
            // 不存在，拦截，返回 401 状态吗（未授权）
            response.setStatus(401);
            return false;
        }
        // 2. 放行
        return true;
    }
}
