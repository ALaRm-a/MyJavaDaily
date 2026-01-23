package com.zhong.Interceptor;

import com.zhong.utils.JWTUtils;
import com.zhong.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

// 加入IOC容器，做拦截器的配置类的时候可以直接注入
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // request能够直接获取请求头的数据
        String token = request.getHeader("Authorization");
        try {
            // 是判断非空？来看有没有令牌，要不要校验令牌的合法性？
            // 只要携带令牌就放行，携带令牌就说明已经登陆过了
            Map<String, Object> stringObjectMap = JWTUtils.parseToken(token);

            // 使用threadLocal添加map,这个是已经解析过的结果，可以直接获取username
            ThreadLocalUtil.set(stringObjectMap);
            return true;// true才能放行
        } catch (Exception e) {
            log.info(e.getMessage()+"没有携带令牌");
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 删除threadLocal中的数据
        ThreadLocalUtil.remove();
    }

}
