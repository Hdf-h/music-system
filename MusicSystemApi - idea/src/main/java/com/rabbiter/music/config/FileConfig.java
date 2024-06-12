package com.rabbiter.music.config;

import com.rabbiter.music.utils.PathUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 定位各种文件或头像地址
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //歌手头像地址
        registry.addResourceHandler("/img/singerPic/**").addResourceLocations(
                "file:///" + PathUtils.getClassLoadRootPath() + "/img/singerPic/"
        );
        //歌单图片地址
        registry.addResourceHandler("/img/songListPic/**").addResourceLocations(
                "file:///" + PathUtils.getClassLoadRootPath() + "/img/songListPic/"
        );
        //歌曲图片地址
        registry.addResourceHandler("/img/songPic/**").addResourceLocations(
                "file:///" + PathUtils.getClassLoadRootPath() + "/img/songPic/"
        );
        //歌曲地址
        registry.addResourceHandler("/song/**").addResourceLocations(
                "file:///" + PathUtils.getClassLoadRootPath()+"/song/"
        );
        //前端用户头像地址
        registry.addResourceHandler("/userImages/**").addResourceLocations(
                "file:///" + PathUtils.getClassLoadRootPath() + "/userImages/"
        );
        //用户头像默认地址
        registry.addResourceHandler("/img/**").addResourceLocations(
                "file:///" + PathUtils.getClassLoadRootPath() + "/img/"
        );
    }
}

















