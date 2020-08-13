package com.raisecom.nacosconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: bbm
 * @Date: 2020/8/12 5:40 下午
 */
@RestController
@RequestMapping("/consumer/v1")
public class ConfigController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String port;

    @RequestMapping(method = RequestMethod.GET, value = "/consumer/{id}")
    public String consumer(@PathVariable("id")String id){
        System.out.println("consumer port:" + port);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://nacos-provider/provider/v1/provider/" + id, String.class);
        System.out.println(forEntity.getBody());
        return forEntity.getBody();
    }
}
