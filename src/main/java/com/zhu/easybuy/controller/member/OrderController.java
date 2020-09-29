package com.zhu.easybuy.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhu.easybuy.pojo.Cart;
import com.zhu.easybuy.pojo.Order;
import com.zhu.easybuy.pojo.Result;
import com.zhu.easybuy.pojo.User;
import com.zhu.easybuy.service.OrderService;
import com.zhu.easybuy.util.CommonUtils;

@RestController
@RequestMapping("/api/member/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private RedisTemplate redisTemplate;

	
	@RequestMapping("/confirmOrder")
	public Result confirmOrder(HttpServletRequest request,String address) {
		User user=CommonUtils.userOfLogin(request,redisTemplate);
		Cart cart=(Cart)redisTemplate.opsForValue().get("Cart_"+user.getId());
		int orderId=orderService.confirmOrder(cart, user, address);
		return new Result(true,"订单添加成功",orderId);
	}
	
	@RequestMapping("/findOrderById")
	public Result findOrderById(HttpServletRequest request,int id) {
		Order order=orderService.findOrderById(id);
		return new Result(true,"订单添加成功",order);
	}
	
	@RequestMapping("/myOrder")
	public Result myOrder(HttpServletRequest request,int pageNum,int pageSize) {
		User user=CommonUtils.userOfLogin(request,redisTemplate);
		return new Result(true,"查询成功",orderService.myOrder(user.getId(),pageNum,pageSize));
	}

}
