package com.eruekaclient.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Redis session共享
 * Created by  Hmq
 * @date on 2018/9/14.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600) // session由spring管理 session超时时间设置
public class RedisSessionConfig {

    /**
     * 禁止发送config命令
     */
    @Bean
    public ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }


}
