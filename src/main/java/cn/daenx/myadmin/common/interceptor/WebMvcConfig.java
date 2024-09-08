package cn.daenx.myadmin.common.interceptor;

import cn.daenx.myadmin.system.cache.ConfigCache;
import cn.daenx.myadmin.login.cache.LoginInfoCache;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private LoginInfoCache loginInfoCache;
    @Resource
    private ConfigCache configCache;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiInterceptor(loginInfoCache, configCache))
                .addPathPatterns("/demo/**")
                .addPathPatterns("/system/**")
                .addPathPatterns("/base/getInfo")
                .excludePathPatterns("/base/login", "/base/logout", "/open");
    }
}
