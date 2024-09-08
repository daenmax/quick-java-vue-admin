package cn.daenx.myadmin.login.domain.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 登录
 */
@Data
public class LoginVo {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

}
