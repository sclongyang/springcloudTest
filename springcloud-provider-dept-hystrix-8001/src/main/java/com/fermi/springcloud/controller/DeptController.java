package com.fermi.springcloud.controller;

import com.fermi.could.pojo.Dept;
import com.fermi.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept queryById( @PathVariable("id") Long id)
    {
        Dept dept = deptService.queryById(id);
        if(dept == null)
        {
            //抛异常就会走熔断方式
            throw new RuntimeException("id:" + id + ",不存在该用户");
        }
        return dept;
    }

    public Dept hystrixGet(@PathVariable("id") Long id){
        return new Dept().setDname("熔断保护").setDeptno(id).setDb_source("wrong");
    }

}
