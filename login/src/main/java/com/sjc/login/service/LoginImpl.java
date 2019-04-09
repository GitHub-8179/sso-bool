package com.sjc.login.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Service;

import com.sjc.login.redis.service.RedisService;

@Service
public class LoginImpl implements ILogin{

	@Autowired
	private RedisService redisService;
	
//    @Cacheable(value = "user", key = "#root.targetClass + #username", unless = "#result eq null")
	@Override
	public Map getToken() {
		String uuid = UUID.randomUUID().toString();
		
		redisService.set("key", uuid,2*1000L);
		
		StringBuffer sb = new StringBuffer();
        redisService.set("str", "str");
        sb.append("str=").append(redisService.get("str").toString()).append(",");
        redisService.hmSet("hmset","key","val");
        sb.append("hmset=").append(redisService.hmGet("hmset","key")).append(",");
        redisService.lPush("list","val");
        sb.append("list=").append(redisService.lRange("list",0,1).toString()).append(",");
        redisService.add("set","val");
        sb.append("set=").append(redisService.setMembers("set").toString()).append(",");
        redisService.zAdd("zset","val1",1);
        redisService.zAdd("zset","val2",2);
        sb.append("zset=").append(redisService.rangeByScore("zset",1,2)).append(",");
        
        setKey();
        
		return null;
	}
	
	@Cacheable(value = "user", key = "#root.targetClass + #username", unless = "#result eq null")
	public void setKey() {
		System.out.println("sssssssss");
	}
	

}
