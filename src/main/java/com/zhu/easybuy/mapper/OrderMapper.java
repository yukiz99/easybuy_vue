package com.zhu.easybuy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhu.easybuy.pojo.Order;

@Mapper
public interface OrderMapper {

	public List<Order> findAllByUser(int userId);
	
	public void add(Order order);//返回自增键，添加orderDetail时需用到

	public List<Order> findAll();

	public void deleteByUser(int userId);

	public Order findById(int id);
	
}
