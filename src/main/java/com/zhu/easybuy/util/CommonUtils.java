package com.zhu.easybuy.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;

import com.zhu.easybuy.pojo.User;

public class CommonUtils {
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	public static User userOfLogin(HttpServletRequest request,RedisTemplate redisTemplate) {
		String token=request.getHeader("token");
		token=(token==null ? "": token);
		User user=(User)redisTemplate.opsForValue().get(token);
		return user;
	}
}
