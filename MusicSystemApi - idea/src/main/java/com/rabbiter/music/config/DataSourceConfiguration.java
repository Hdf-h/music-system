package com.rabbiter.music.config;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.ExceptionSorter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Description
 * Author: rabbiter
 * Date: 2020/2/26 23:39
 **/
@Configuration
public class DataSourceConfiguration {
  @Value("${spring.datasource.driver-class-name}")
  private String jdbcDriver;
  @Value("${spring.datasource.url}")
  private String jdbcUrl;
  @Value("${spring.datasource.username}")
  private String jdbcUsername;
  @Value("${spring.datasource.password}")
  private String jdbcPassword;

  @Bean(name="dataSource")
  public DruidDataSource createDataSource() throws Exception {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName(jdbcDriver);
    dataSource.setUrl(jdbcUrl);
    dataSource.setUsername(jdbcUsername);
    dataSource.setPassword(jdbcPassword);

    // 关闭连接后不自动commit
    dataSource.setDefaultAutoCommit(false);
    // 设置连接异常处理
    dataSource.setBreakAfterAcquireFailure(true);
    // 将SQLException抛出重要配置
    dataSource.setFailFast(true);
    dataSource.setConnectionErrorRetryAttempts(0);
    // 配置自定义的异常处理器
    dataSource.setExceptionSorter(new CustomExceptionSorter());

    // 关闭Druid连接池内部的异常处理
//    dataSource.setFilters("stat");
    return dataSource;
  }

}

class CustomExceptionSorter implements ExceptionSorter {

  @Override
  public boolean isExceptionFatal(SQLException e) {
    // 将所有异常视为致命异常，即抛出到上层
    // 打印异常堆栈信息
    e.printStackTrace();
    return true;
  }

  @Override
  public void configFromProperties(Properties properties) {
    // 配置信息可以为空
  }
}
