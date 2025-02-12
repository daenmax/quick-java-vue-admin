package cn.daenx.myadmin.modules.login.cache;

import cn.daenx.myadmin.framework.common.exception.LoginException;
import cn.daenx.myadmin.modules.login.domain.vo.LoginInfoCacheVo;
import cn.hutool.core.lang.UUID;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoginInfoCache {
    private List<LoginInfoCacheVo> loginInfos;

    public LoginInfoCache() {
        this.loginInfos = new ArrayList<>();
    }

    public LoginInfoCacheVo login(String username, String password) {
        Boolean isExist = false;
        LoginInfoCacheVo info = null;
        for (LoginInfoCacheVo loginInfo : loginInfos) {
            if (loginInfo.getUsername().equals(username)) {
                info = loginInfo;
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            info = new LoginInfoCacheVo();
        }
        info.setUsername(username);
        info.setPassword(password);
        info.setToken(UUID.fastUUID().toString());
        info.setLoginTime(LocalDateTime.now());
        //登录有效期为1天
        info.setExpireTime(info.getLoginTime().plusDays(1));
        if (!isExist) {
            this.loginInfos.add(info);
        }
        return info;
    }

    public LoginInfoCacheVo getInfo(String token) {
        token = handleToken(token);
        for (LoginInfoCacheVo loginInfo : loginInfos) {
            if (loginInfo.getToken().equals(token)) {
                return loginInfo;
            }
        }
        throw new LoginException("用户未登录");
    }

    public void logout(String token) {
        token = handleToken(token);
        for (LoginInfoCacheVo loginInfo : loginInfos) {
            if (loginInfo.getToken().equals(token)) {
                loginInfo.setToken(null);
                return;
            }
        }
    }

    public void check(String token) {
        token = handleToken(token);
        for (LoginInfoCacheVo loginInfo : loginInfos) {
            if (token.equals(loginInfo.getToken())) {
                //判断expireTime和当前时间
                if (loginInfo.getExpireTime().isBefore(LocalDateTime.now())) {
                    throw new LoginException("登录超时");
                }
                return;
            }
        }
        throw new LoginException("用户未登录");
    }

    private String handleToken(String token){
        return token.replaceAll("Bearer ", "");
    }
}
