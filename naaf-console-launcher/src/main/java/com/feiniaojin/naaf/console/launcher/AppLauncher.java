package com.feiniaojin.naaf.console.launcher;

import com.feiniaojin.naaf.ngr.starter.EnableGracefulResponse;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

/**
 * @author <a href="mailto:qinyujie@gingo.cn">Yujie</a>
 * @version 0.1
 */
@SpringBootApplication(scanBasePackages = {
        "com.feiniaojin.naaf"
})
@EnableGracefulResponse
@MapperScans(@MapperScan({"com.feiniaojin.naaf.console.mapper"}))
@EnableJdbcRepositories(basePackages = {
        "com.feiniaojin.naaf.console.repository"
})
@EnableJdbcAuditing
public class AppLauncher {

    public static void main(String[] args) {
        SpringApplication.run(AppLauncher.class, args);
    }
}
