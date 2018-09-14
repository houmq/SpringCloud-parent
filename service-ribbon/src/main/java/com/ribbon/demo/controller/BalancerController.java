package com.ribbon.demo.controller;

import com.ribbon.demo.service.LoadBalancerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by  Hmq
 * @date on 2018/9/14.
 */
@RestController
@RequestMapping(value = "/ribbon")
public class BalancerController {


    private Logger logger = LoggerFactory.getLogger(BalancerController.class);

    @Autowired
    private LoadBalancerService loadBalancerService;



    @GetMapping(value = "/load")
    public String balancer(String say){
        logger.info("负载均衡ribbon");
        return loadBalancerService.info(say);
    }



}
