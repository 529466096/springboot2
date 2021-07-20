package com.noodles.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @filename MybatisPlusConfig
 * @description
 * @author noodles
 * @date 2021/7/20 15:42
 */
@Configuration
@MapperScan("com.noodles.dao")
public class MybatisPlusConfig {


}
