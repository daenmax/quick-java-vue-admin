package cn.daenx.myadmin.system.controller;


import cn.daenx.myadmin.common.vo.Result;
import cn.daenx.myadmin.system.domain.po.SystemConfig;
import cn.daenx.myadmin.system.domain.vo.SystemConfigUpdVo;
import cn.daenx.myadmin.system.service.SystemConfigService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
        SystemConfig systemConfig = systemConfigService.getInfo();
        return Result.ok(systemConfig);
    }

    /**
     * 修改
     *
     * @param vo
     * @return
     */
    @PostMapping("/edit")
    public Result edit(@Validated @RequestBody SystemConfigUpdVo vo) {
        systemConfigService.editInfo(vo);
        return Result.ok();
    }


}
