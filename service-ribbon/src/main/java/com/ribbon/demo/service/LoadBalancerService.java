package com.ribbon.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Created by  Hmq
 * @date on 2018/9/14.
 */
@Service
public class LoadBalancerService {

    private String URL = "http://eureka-client/info";


    @Autowired
    private RestTemplate restTemplate;


    public String info(String say){
        return restTemplate.getForObject(URL,String.class) + ":" +say;
    }




}
