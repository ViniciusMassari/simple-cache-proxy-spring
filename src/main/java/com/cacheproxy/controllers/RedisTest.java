package com.cacheproxy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proxy")
public class RedisTest implements Runnable {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/")
    public ResponseEntity<Object> redis() {
        var key = this.redisTemplate.opsForValue().get("test");
        if (key == null)
            return ResponseEntity.badRequest().body("Key doesn't exist");
        System.out.println();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity<Object> redisPost() {
        var redisResponse = this.redisTemplate.opsForValue().append("test", "value");
        if (redisResponse.equals(0))
            return ResponseEntity.badRequest().body("Error during creation");
        return ResponseEntity.ok().build();
    }

    @Override
    public void run() {
        System.out.println("running");
    }
}
