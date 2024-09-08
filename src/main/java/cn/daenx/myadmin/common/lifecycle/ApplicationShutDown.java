package cn.daenx.myadmin.common.lifecycle;

import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 项目停止时，执行
 */
@Component
@Slf4j
public class ApplicationShutDown {
    @PreDestroy
    public void preDestroy() {
        //在这里写代码，停止你创建的一些线程之类的
        log.info("[life cycle] system is close");
    }
}
