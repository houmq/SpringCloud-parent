package com.eruekaclient.demo.service;

import com.eruekaclient.demo.dao.HelloDao;
import com.eruekaclient.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by  Hmq
 * @date on 2018/8/30.
 */
@Service
public class HelloService {

    @Autowired
    private HelloDao helloDao;

    @Transactional(isolation = Isolation.SERIALIZABLE,rollbackFor = Exception.class)
    public String sayHello(String say){
        return helloDao.hello(say) + ":" + say;
    }


    @Cacheable(value = "keyGenerator",condition = "#id eq 1")
    public User getUser(Integer id){
        System.out.println("保存缓存");
        return new User(1,"hmq",25);
    }

    @CachePut(value = "keyGenerator",unless = "#result.id eq 2")
    public User putUser(User user){
        System.out.println("更新缓存");
        return new User(2,"hmq",26);
    }


    public int update(@Param("loginname")String loginname){
        return helloDao.update(loginname);
    }




}
