package com.example.demo.Controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Config.RedisUtil;

@RestController
@RequestMapping("/hello")
public class RedisController
{
    /**
     * 注入redis工具类
     */
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/hi/{name}")
    public String hi(@PathVariable("name") String name)
    {
        System.out.println("name="+name);

        if((Object)(redisUtil.get(name))!=null)
        {
        	System.out.println("name对应的结果不为空");
        	System.out.println("name对应的结果为"+redisUtil.get(name).toString());
            return redisUtil.get(name).toString();
        }
        else {
        	System.out.println("name对应的结果为空");
            redisUtil.set(name,"My name is "+name+"!",60);

            return redisUtil.get(name).toString();
        }
    }
    
    
    @GetMapping("/list/{name}")
    public List<?> addlist(@PathVariable("name") String name)
    {
        System.out.println("name="+name);

        if(redisUtil.get(name)!=null)
        {
        	System.out.println("name对应的结果不为空");
        	System.out.println("name对应的结果为"+redisUtil.range(name, 0, -1));
            return redisUtil.range(name, 0, -1);
        }
        else {
        	System.out.println("name对应的结果为空");
            

            List<Integer> list= new ArrayList() ;
            list. add(1);
            list.add(2);
            list.add(3);
            redisUtil.leftPush(name, list);
            
            System.out.println("hhh3写入redis成功");
            
            //return redisUtil.get(name).toString();
            List a = new ArrayList<>();
            a = redisUtil.range(name, 0, -1);
            System.out.println(a);
            return a;
        }
    }
    
    
    
}