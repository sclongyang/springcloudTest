package com.fermi.springcloud.controller;

import com.fermi.could.pojo.Dept;
import com.fermi.springcloud.service.DeptService;
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

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept){
        return  deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept queryById( @PathVariable("id") Long id){
        return deptService.queryById(id);
    }
    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }

    @GetMapping("/dept/discovery")
    public Object discovery(){
        //获取微服务列表的清单
        List<String> services = discoveryClient.getServices();
        System.out.println("discovery:" + services);

        //遍历
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRING-CLOUD-PROVIDER-DEPT-8001");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getHost());
            System.out.println(instance.getPort());
            System.out.println(instance.getUri());
            System.out.println(instance.getServiceId());
        }
        return discoveryClient;
    }
}
