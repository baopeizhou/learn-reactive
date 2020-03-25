package org.bob.learn.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.bob.learn.common.util.RedisUtils;
import org.bob.learn.service.TestService;
import org.bob.learn.web.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    private static AtomicLong atomicLong = new AtomicLong(1);

    @Autowired
    private TestService testService;

    @GetMapping("/get")
    public Mono<String> test(){
        String mac = mac();
        log.info(RedisUtils.entries(mac).toString());
        return Mono.just(mac);
    }


    private String mac(){
        return "MAC"+String.format("%09d", atomicLong.getAndIncrement());
    }
}