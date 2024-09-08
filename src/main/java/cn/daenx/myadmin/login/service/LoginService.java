package cn.daenx.myadmin.login.service;

import cn.daenx.myadmin.login.domain.vo.LoginVo;

import java.util.Map;

public interface LoginService {
    /**
     * PC登录
     *
     * @param vo
     * @return
     */
    String login(LoginVo vo);

    /**
     * 退出登录
     */
    void logout(String token);

    /**
     * 获取用户信息
     *
     * @return
     */
    Map<String, Object> getInfo(String token);

}
