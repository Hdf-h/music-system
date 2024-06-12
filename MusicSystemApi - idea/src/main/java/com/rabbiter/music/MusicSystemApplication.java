package com.rabbiter.music;

import com.rabbiter.music.utils.PathUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rabbiter.music.dao")
public class MusicSystemApplication {

    public static void main(String[] args) {
        System.out.println("Project Path: " + PathUtils.getClassLoadRootPath());
        SpringApplication.run(MusicSystemApplication.class, args);
    }

}
