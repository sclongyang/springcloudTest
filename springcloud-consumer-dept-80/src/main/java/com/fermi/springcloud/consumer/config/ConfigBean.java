package com.fermi.springcloud.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class ConfigBean {

    //负载均衡: 实现该接口IRule, 就可实现算法的自定义
    //RoundRobinRule 轮询(默认方式)
    //RandomRule 随机
    // AvailabilityFilteringRule: 过滤掉跳闸(崩溃, 有故障)的服务, 剩下的轮询
    //RetryRule: 轮询, 若服务获取失败, 就在指定时间内重试
    @LoadBalanced //Ribbon负载均衡
    @Bean
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }
}
