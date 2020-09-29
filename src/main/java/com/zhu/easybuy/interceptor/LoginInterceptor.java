package com.zhu.easybuy.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.zhu.easybuy.pojo.Result;
import com.zhu.easybuy.pojo.User;
import com.zhu.easybuy.util.CommonUtils;

//具体的权限实现
@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	User user=CommonUtils.userOfLogin(request, redisTemplate);
        if (user==null) {
            setReturn(response,"请先登录");
            return false;
        }
        //已登录。
        String token=request.getHeader("token");
        redisTemplate.expire(token, 30L, TimeUnit.MINUTES);      
        
        if(!isHavePermiss(user,request.getRequestURI())) {
        	setReturn(response,"没有权限");
        	return false;
        }
        return true;

    }
  
    
    private boolean isHavePermiss(User user, String url){
    	if(user.getType()==1 || url.contains("/api/member")) {
        	return true;
        }
        return false;
    }
    
    
    //返回错误信息
    private static void setReturn(HttpServletResponse response, String msg) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        Result result = new Result(false,msg);
        String json = JSON.toJSONString(result);
        httpResponse.getWriter().print(json);
    }
}

