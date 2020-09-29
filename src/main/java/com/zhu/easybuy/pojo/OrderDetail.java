package com.zhu.easybuy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetail implements Serializable{

	private Integer id;//主键
	
	private Integer orderId;//订单主键
	private Integer productId;//商品主键
	private Integer quantity;//数量
	private Float cost;//消费
	
	private Product product;
}
