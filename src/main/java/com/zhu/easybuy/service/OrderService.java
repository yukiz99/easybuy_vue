package com.zhu.easybuy.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhu.easybuy.pojo.Cart;
import com.zhu.easybuy.pojo.Order;
import com.zhu.easybuy.pojo.User;

public interface OrderService {
	
	public int confirmOrder(Cart cart,User user,String address);
	
	public PageInfo<Order> myOrder(int userId,int pageNum,int pageSize);
	
	
	//admin
	public PageInfo<Order> AllOrder(int pageNum,int pageSize);

	public Order findOrderById(int id);


}
