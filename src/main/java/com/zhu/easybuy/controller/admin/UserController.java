package com.zhu.easybuy.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhu.easybuy.pojo.Result;
import com.zhu.easybuy.pojo.User;
import com.zhu.easybuy.service.UserService;
import com.zhu.easybuy.util.CommonUtils;

@RestController("AdminUserController")
@RequestMapping("/api/admin/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RedisTemplate redisTemplate;
	
	@RequestMapping("/allUser")
	public Result allUser(int pageNum,int pageSize) {
		return new Result(true,"查询成功",userService.allUser(pageNum,pageSize));
	}

	  
	
	
	//删除时，应判断是否为admin管理员，判断是否为其他管理员。删除后应将订单也删除
	@RequestMapping("/deleteUser")
	public Result deleteUser(HttpServletRequest request,int id) {
		User userDelete=userService.findUserById(id);
		User userLogin=CommonUtils.userOfLogin(request, redisTemplate);
		if(userDelete.getLoginName().equals("admin")) {//系统管理员不可删
			return new Result(false,"admin不可删除");
		}
		//非系统管理员不可删除普通管理员
		if(!userLogin.getLoginName().equals("admin") && userDelete.getType()==1 ) {
		   return new Result(false,"普通管理员不可删除其他管理员");
		}
		userService.deleteUser(id);//service中删除了订单、地址之类的信息
		return new Result(true,"删除用户成功");
	}
	
	 
	@RequestMapping("/findUserById")
	public Result findUserById(int id) {
		  User user=userService.findUserById(id);
		  return new Result(true,"查询用户成功",user);
	}
	
	
	@RequestMapping("/updateUser")
	public Result updateUser(User user) {
		  userService.updateUser(user);
		  return new Result(true,"更新用户成功");
	}
}
