package cn.daenx.myadmin.system.controller;


import cn.daenx.myadmin.common.vo.Result;
import cn.daenx.myadmin.system.service.SystemConfigService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 系统配置
 */
@RestController
@RequestMapping("/system/config")
@Slf4j
public class SystemConfigController {
    @Resource
    private SystemConfigService systemConfigService;


    /**
     * 查询
     *
     * @return
     */
    @GetMapping(value = "/query")
    public Result query() {
        Map<String, String> map = systemConfigService.getInfo();
        return Result.ok(map);
    }

    /**
     * 修改
     *
     * @param map
     * @return
     */
    @PostMapping("/edit")
    public Result edit(@RequestBody Map<String, String> map) {
        systemConfigService.editInfo(map);
        return Result.ok();
    }


}
