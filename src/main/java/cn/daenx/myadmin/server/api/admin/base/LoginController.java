package cn.daenx.myadmin.server.api.admin.base;

import cn.daenx.myadmin.framework.common.vo.Result;
import cn.daenx.myadmin.modules.login.domain.vo.LoginVo;
import cn.daenx.myadmin.modules.login.service.LoginService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class LoginController {
    @Resource
    private LoginService loginService;

    /**
     * PC登录
     *
     * @param vo
     * @return
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginVo vo) {
        String token = loginService.login(vo);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        return Result.ok(map);
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        loginService.logout(token);
        return Result.ok("退出成功");
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/getInfo")
    public Result getInfo(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Map<String, Object> map = loginService.getInfo(token);
        return Result.ok(map);
    }

}
