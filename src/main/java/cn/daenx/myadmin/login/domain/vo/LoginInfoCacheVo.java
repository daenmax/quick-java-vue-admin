package cn.daenx.myadmin.login.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 登录缓存信息
 */
@Data
public class LoginInfoCacheVo {

    private String username;
    private String password;
    private String token;
    private LocalDateTime loginTime;
    /**
     * 登录到期时间
     */
    private LocalDateTime expireTime;

}
