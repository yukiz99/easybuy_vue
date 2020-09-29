package com.zhu.easybuy.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhu.easybuy.pojo.Result;
import com.zhu.easybuy.pojo.UserAddress;
import com.zhu.easybuy.service.UserAddressService;
import com.zhu.easybuy.util.CommonUtils;

@RestController
@RequestMapping("/api/member/address")
public class UserAddressController {
	
	@Autowired
	private UserAddressService addressService;
	@Autowired
	private RedisTemplate redisTemplate; 
	
	private static final int addressMaxSize=3;
	
	@RequestMapping("/findAllByUser")
	public Result findAllByUser(HttpServletRequest request){
		int userId=CommonUtils.userOfLogin(request, redisTemplate).getId();
		List<UserAddress> list=addressService.findAllByUserId(userId);
		return new Result(true,"查询成功",list);
	}
	@RequestMapping("/findAddressById")
	public Result findAddressById(int id) {
		return new Result(true,"查询成功",addressService.findAddressById(id));
	}
	
	@RequestMapping("/addAddress")
	public Result addAddress(HttpServletRequest request,UserAddress address) {
		int userId=CommonUtils.userOfLogin(request, redisTemplate).getId();
		address.setUserId(userId);
		List<UserAddress> list=addressService.findAllByUserId(userId);
		if(list!=null&&list.size()==addressMaxSize) {
			return new Result(false,"添加失败，地址个数已满");
		}
		addressService.addAddress(address);
		return new Result(true,"添加成功");
	}
	
	@RequestMapping("/updateAddress")
	public Result updateAddress(UserAddress address) {
		addressService.updateAddress(address);
		return new Result(true,"更新成功");
	}
	
	@RequestMapping("/deleteAddress")
	public Result deleteAddress(int id) {
		addressService.deleteAddress(id);
		return new Result(true,"删除成功");
	}
	
	//设置为默认地址
	@RequestMapping("/setDefault")
	public Result setDefault(int id) {
		addressService.setDefault(id);
		return new Result(true,"设置成功");
	}
	
	
}
