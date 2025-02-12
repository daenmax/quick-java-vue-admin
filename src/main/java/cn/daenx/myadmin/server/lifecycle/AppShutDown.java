package cn.daenx.myadmin.server.lifecycle;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 项目停止时，执行
 */
@Component
@Slf4j
public class AppShutDown {
    @PreDestroy
    public void preDestroy() {
        //在这里写代码，停止你创建的一些线程之类的
        log.info("[life cycle] system is close");
    }
}
