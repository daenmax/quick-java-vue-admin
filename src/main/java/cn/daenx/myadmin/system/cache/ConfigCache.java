package cn.daenx.myadmin.system.cache;

import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class ConfigCache {
    private Map<String, String> map;

    public Map<String, String> getConfig() {
        return map;
    }

    public void setConfig(Map<String, String> map) {
        this.map = map;
    }
}
