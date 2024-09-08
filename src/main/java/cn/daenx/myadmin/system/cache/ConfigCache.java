package cn.daenx.myadmin.system.cache;

import cn.daenx.myadmin.system.domain.po.SystemConfig;
import org.springframework.stereotype.Component;


@Component
public class ConfigCache {
    private SystemConfig config;

    public SystemConfig getConfig() {
        return config;
    }

    public void setConfig(SystemConfig config) {
        this.config = config;
    }
}
