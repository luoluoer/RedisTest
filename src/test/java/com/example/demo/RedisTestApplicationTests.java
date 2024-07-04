package com.example.demo;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RedisTestApplicationTests {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
    @Test
    void contextLoads() {
    	System.out.println("redis");
    	stringRedisTemplate.opsForValue().set("c","ccc",60, TimeUnit.SECONDS);
    	System.out.println(stringRedisTemplate.opsForValue().get("c"));
    	
    }
    
    
}
