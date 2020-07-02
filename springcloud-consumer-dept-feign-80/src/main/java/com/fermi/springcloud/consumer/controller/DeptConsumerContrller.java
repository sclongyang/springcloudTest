package com.fermi.springcloud.consumer.controller;

import com.fermi.could.pojo.Dept;
import com.fermi.could.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerContrller {
    //消费者不应该有自己的service层, 此service是远程的
    @Autowired
    DeptClientService deptClientService = null;


    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return deptClientService.addDept(dept);
    }

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        System.out.println("step in consumer feign-80: get " +id);
        return deptClientService.queryById(id);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return deptClientService.queryAll();
    }
}
