package cn.daenx.myadmin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication
@Slf4j
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        log.info("================== =============================================== ==================");
        log.info("================== =============================================== ==================");
        log.info("==================      Server is ok ,  By Daen QQ 1330166564      ==================");
    }

}
