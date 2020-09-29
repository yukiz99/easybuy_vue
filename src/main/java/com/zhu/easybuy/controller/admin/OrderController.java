package com.zhu.easybuy.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhu.easybuy.pojo.Order;
import com.zhu.easybuy.pojo.Result;
import com.zhu.easybuy.service.OrderService;

@RestController("AdminOrderController")
@RequestMapping("/api/admin/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping("/allOrder")
	public Result allOrder(int pageNum,int pageSize){
		return new Result(true,"查询成功",orderService.AllOrder(pageNum,pageSize));
	}

	
}
