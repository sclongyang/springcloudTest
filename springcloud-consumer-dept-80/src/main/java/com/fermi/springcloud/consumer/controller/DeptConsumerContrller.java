package com.fermi.springcloud.consumer.controller;

import com.fermi.could.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerContrller {
    //消费者不应该有service层
    //RestTemplate供我们 直接调用
    @Autowired
    private  RestTemplate restTemplate;//提供多种便捷访问http服务的方法, 是简单的restfull服务模板

//    private static final String RESTFULL_URL_PREFIX = "http://localhost:8001/dept";
    //Ribbon的负载均衡与Eureka整合之后, 可以直接调用(通过微服务名称), 无需关心ip地址和端口号
    private static final String RESTFULL_URL_PREFIX = "http://SPRING-CLOUD-PROVIDER-DEPT";

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
         return restTemplate.postForObject(RESTFULL_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(RESTFULL_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(RESTFULL_URL_PREFIX + "/dept/list", List.class);
    }
}
