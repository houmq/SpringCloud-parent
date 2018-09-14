package com.eruekaclient.demo.cache.business;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * 自定义实现业务层缓存
 * 使用REDIS实现配合
 * Created by  Hmq
 * @date on 2018/8/31.
 */
@Configuration
@EnableCaching//启用缓存，这个注解很重要
public class CustonBusinessCache {




//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        return RedisCacheManager.create(redisConnectionFactory);
//    }

    /**
     * cache key生成策略
     * @author Hmq
     * @date 2018/9/3 15:05
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuffer sb = new StringBuffer("::");
                sb.append(target.getClass().getName())
                        .append("_")
                        .append(method.getName())
                        .append("_");
                for(Object obj:params){
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

}
