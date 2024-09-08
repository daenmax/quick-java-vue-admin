package cn.daenx.myadmin.login.service.impl;

import cn.daenx.myadmin.common.exception.MyException;
import cn.daenx.myadmin.login.cache.LoginInfoCache;
import cn.daenx.myadmin.login.config.SystemAdmin;
import cn.daenx.myadmin.login.domain.vo.LoginInfoCacheVo;
import cn.daenx.myadmin.login.domain.vo.LoginVo;
import cn.daenx.myadmin.login.service.LoginService;
import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginInfoCache loginInfoCache;
    @Resource
    private SystemAdmin systemAdmin;

    /**
     * PC登录
     *
     * @param vo
     * @return
     */
    @Override
    public String login(LoginVo vo) {
        String password = systemAdmin.getUserList().get(vo.getUsername());
        if (ObjectUtil.isEmpty(password)) {
            throw new MyException("用户名不存在");
        }
        if (!password.equals(vo.getPassword())) {
            throw new MyException("密码错误");
        }
        LoginInfoCacheVo cacheVo = loginInfoCache.login(vo.getUsername(), vo.getPassword());
        return cacheVo.getToken();
    }

    /**
     * 退出登录
     */
    @Override
    public void logout(String token) {
        loginInfoCache.logout(token);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public Map<String, Object> getInfo(String token) {
        LoginInfoCacheVo info = loginInfoCache.getInfo(token);
        Map<String, Object> map = new HashMap<>();
        map.put("user", info);
        map.put("roles", Arrays.asList("admin"));
        map.put("permissions", Arrays.asList("*:*:*"));
        return map;
    }
}
