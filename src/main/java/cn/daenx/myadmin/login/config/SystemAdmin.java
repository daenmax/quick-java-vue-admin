package cn.daenx.myadmin.login.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "system")
public class SystemAdmin {

    private Map<String, String> userList;
}
