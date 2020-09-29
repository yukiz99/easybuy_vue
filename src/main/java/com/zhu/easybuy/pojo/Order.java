package com.zhu.easybuy.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Order implements Serializable{

	private Integer id;//主键


	
	private Integer userId;//用户主键
	private String loginName;//
	private String userAddress;//用户地址
	private Date createTime;//创建时间
	private Float cost;//总消费
	private String serialNumber;//订单号
	
	private List<OrderDetail> list;
}
