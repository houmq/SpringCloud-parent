package com.eruekaclient.demo.cache.store;

import com.eruekaclient.demo.config.SpringContextHolder;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 自定义实现mybatiss二级缓存
 * 使用REDIS做数据存储
 * Created by  Hmq
 * @date on 2018/8/31.
 */
public class CustomMybatisCache implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(CustomMybatisCache.class);

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id;
    private RedisTemplate<Object,Object> redisTemplate;
    private static final long EXPIRE_TIME_IN_MINUTES = 30;

    public CustomMybatisCache(String id){
        if(id == null) {
            throw new IllegalArgumentException("Cache instance requires an ID");
        }
        this.id = id;
    }


    private RedisTemplate getRedisTemplate() {
        if(null == this.redisTemplate){
            logger.debug("set redisTemplate");
            this.redisTemplate = SpringContextHolder.getBean("redisTemplate");
        }
        return this.redisTemplate;
    }


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        ValueOperations<Object,Object> opsForValue = this.getRedisTemplate().opsForValue();
        opsForValue.set(key.toString(), value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
        logger.debug("save object into redis.");
    }

    @Override
    public Object getObject(Object key) {
        ValueOperations<Object,Object> opsForValue = this.getRedisTemplate().opsForValue();
        logger.debug("Get cached query result from redis");
        return opsForValue.get(key.toString());
    }

    @Override
    public Object removeObject(Object key) {
        this.getRedisTemplate().delete(key.toString());
        logger.debug("Remove cached query result from redis");
        return key;
    }

    @Override
    public void clear() {
        this.getRedisTemplate().execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                logger.debug("flush redis");
                redisConnection.flushDb();
                return null;
            }
        });
    }

    @Override
    public int getSize() {

        Long size = (Long)this.getRedisTemplate().execute(new RedisCallback() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                logger.debug("flush redis");
                return redisConnection.dbSize();
            }
        });
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}
