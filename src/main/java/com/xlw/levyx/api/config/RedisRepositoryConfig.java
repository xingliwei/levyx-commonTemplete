package com.xlw.levyx.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * Created by levyx on 2017/7/21.
 * @EnableRedisRepositories 中配置的包是定义的repository的包，可以多个，用逗号隔开
 */
@Configuration
@EnableRedisRepositories(basePackages = {"com.xlw.levyx.api.repository"})
public class RedisRepositoryConfig {
}
