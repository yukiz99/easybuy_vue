package com.zhu.easybuy.controller.member;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mysql.fabric.Response;
import com.zhu.easybuy.pojo.Result;
import com.zhu.easybuy.pojo.User;
import com.zhu.easybuy.service.UserService;
import com.zhu.easybuy.util.CommonUtils;

@RestController
@RequestMapping("/api/member/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RedisTemplate redisTemplate;
	
	
	@RequestMapping("/login")
	public Result login(String loginName,String password,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		User user=userService.login(loginName,DigestUtils.md5Hex(password));//md5加密后
		if(user!=null) {
			//生成令牌,存到Redis数据库
			String token=CommonUtils.uuid();
			redisTemplate.opsForValue().set(token,user,30L, TimeUnit.MINUTES);//存30分钟，Long型数据带上L
			
			return new Result(true,"登录成功",token);
		}else {
			return new Result(false,"用户名或密码错误，登录失败");
		}
	}
	
//	
//	@RequestMapping("/getVerifyCode")
//	public Result getVerifyCode(HttpServletRequest request) {
//			VerifyCode code=new VerifyCode();
//			BufferedImage image=code.getImage();
//			String text=code.getText();
//			//以ip来作为key
//			String ip = request.getRemoteAddr();
//			redisTemplate.opsForValue().set(ip+"_VerifyCode",text,10L,TimeUnit.MINUTES);//存10分钟
//			
//			return new Result(true,"成功生成验证码",image);
//	}
//	
	
	@RequestMapping("/register")
	public Result register(User user,String verifyCode,HttpServletRequest request) {
		if(userService.findUserByLoginName(user.getLoginName())==null) {
				userService.register(user);
				return new Result(true,"注册成功");
		}else {
			return new Result(false,"该登录名已存在，注册失败");
		}
	}
	
	/**
	 * 从redis中获取登录用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUserOfLogin")
	public Result getUserOfLogin(HttpServletRequest request) {
		User user=CommonUtils.userOfLogin(request, redisTemplate);
		if(user!=null) {
			return new Result(true,"获取登录用户成功",user);
		}else {
			return new Result(false,"获取登录用户失败");
		}	
	}
	
	@RequestMapping("/logout")
	public Result logout(HttpServletRequest request) {
		String token=request.getHeader("token");
		redisTemplate.delete(token);
		return new Result(true,"退出成功");
	}
	
	//更新的时候把redis中user也改掉
	@RequestMapping("updateUser")
	public Result updateUser(HttpServletRequest request,User user) {
		userService.updateUser(user);
		redisTemplate.opsForValue().set(request.getHeader("token"),user);//修改redis中用户
		return new Result(true,"更新用户成功");
	}
	
	
	
}
