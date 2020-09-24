package com.faber.airmgr;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.faber.airmgr")
@EnableCaching
public class AppLaunch {
    public static void main(String[] args) {
        SpringApplication.run(AppLaunch.class, args);
    }
}
