package cn.daenx.myadmin.common.lifecycle;

import cn.daenx.myadmin.system.service.SystemConfigService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动后，初始化
 */
@Component
@Slf4j
public class ApplicationRunnerImpl implements ApplicationRunner {
    @Resource
    private SystemConfigService systemConfigService;


    @Override
    public void run(ApplicationArguments args) {
        log.info("[life cycle] Initializing system configuration to memory cache...");
        systemConfigService.initCache();
        log.info("[life cycle] init is ok");
    }

}
