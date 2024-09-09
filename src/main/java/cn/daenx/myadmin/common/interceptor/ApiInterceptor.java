package cn.daenx.myadmin.common.interceptor;

import cn.daenx.myadmin.common.exception.MyException;
import cn.daenx.myadmin.system.cache.ConfigCache;
import cn.daenx.myadmin.system.domain.po.SystemConfig;
import cn.daenx.myadmin.common.utils.MyUtil;
import cn.daenx.myadmin.login.cache.LoginInfoCache;
import cn.hutool.core.util.ObjectUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public class ApiInterceptor implements HandlerInterceptor {
    private LoginInfoCache loginInfoCache;
    private ConfigCache configCache;

    public ApiInterceptor(LoginInfoCache loginInfoCache, ConfigCache configCache) {
        this.loginInfoCache = loginInfoCache;
        this.configCache = configCache;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        MyUtil.handleTestUse();
        String token = request.getHeader("Authorization");
        String apikey = request.getHeader("Apikey");
        String requestURI = request.getRequestURI();
        if (ObjectUtil.isNotEmpty(token)) {
            loginInfoCache.check(token);
            return true;
        }
        if (requestURI.contains("/system/config")) {
            throw new MyException("该接口禁止通过API调用");
        }
        Map<String, String> configMap = configCache.getConfig();
        if (configMap.get("manager_api_lock").equals("1")) {
            throw new MyException("管理员未开启后台管理API接口");
        }
        if (ObjectUtil.isEmpty(configMap.get("manager_api_key"))) {
            return true;
        }
        if (ObjectUtil.isEmpty(apikey)) {
            throw new MyException("请在Header中设置Apikey参数");
        }
        if (!apikey.equals(configMap.get("manager_api_key"))) {
            throw new MyException("Apikey错误");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
