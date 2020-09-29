package com.zhu.easybuy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhu.easybuy.pojo.OrderDetail;

@Mapper
public interface OrderDetailMapper {
	
	public void add(OrderDetail detail);

	public List<OrderDetail> findAllByOrderId(int orderId);

	public void deleteByOrder(int orderId);

}
