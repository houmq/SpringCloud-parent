package com.eruekaclient.demo.controller;

import com.eruekaclient.demo.config.annotation.JSON;
import com.eruekaclient.demo.config.annotation.JSONS;
import com.eruekaclient.demo.entity.User;
import com.eruekaclient.demo.service.HelloService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Hmq
 * @date on 2018/8/25.
 */
@RestController
@RequestMapping(value = "/")
public class HelloController {

    private static Logger logger = LoggerFactory.getLogger(HelloController.class);


    @Autowired
    private HelloService helloService;


    @Value("${server.port}")
    private int port;


    @RequestMapping(value = "/say",method = RequestMethod.GET)
    public String helloCloud(@RequestParam(value = "say")String say){
        logger.info("this is springcloud demo.");
        String s = helloService.sayHello(say);
        return s + port;
    }


    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String info(){
        return "springCloud-info:" + port;
    }



    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;



    @ApiOperation(value = "redis String ",notes = "operation")
    @RequestMapping(value = "/stringops",method = RequestMethod.GET)
    public String redisStringOps(@RequestParam(value = "key")String key){
        stringRedisTemplate.opsForValue().set(key,"frank");
        User user = new User(1,"zhangsan",25);

        redisTemplate.opsForValue().set("template",user);
        redisTemplate.opsForValue().set("template","保存userentiti");


        return "";
    }

    @ApiOperation(value = "修改",notes = "刷新缓存")
    @PutMapping(value = "/update")
    public int mybatisCacheRefresh(@RequestParam(value = "loginname")String loginname){
        return helloService.update(loginname);
    }



    @GetMapping(value = "/cache/{id}")
    public User redisCache(@PathVariable(value = "id")Integer id){
        return helloService.getUser(id);
    }

    @PutMapping(value = "/cache")
    @JSONS(value = {
            @JSON(type = User.class,filter = "id,age")
    })
    public User refreshRedisCache(@RequestBody User user){
        return helloService.putUser(user);
    }







}
