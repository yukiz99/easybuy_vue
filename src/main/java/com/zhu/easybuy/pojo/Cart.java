package com.zhu.easybuy.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Cart implements Serializable{
	
	private float total;
	Map<Integer,CartItem> map;
	
	@Override
	public String toString() {
		return "Cart [total=" + total + ", map=" + map + "]";
	}
	
	
	
//	private Integer id;
//	private String loginName;
//	private Integer productId;
//	private String productName;
//	private String categoryName;
//	private Double price;
//	private Integer num;
//	private String fileName;
//
//	public Cart() {
//		super();
//	}
//
//
//	public Cart(Integer id, String loginName, Integer productId, String productName, String categoryName, Double price,
//			Integer num, String fileName) {
//		super();
//		this.id = id;
//		this.loginName = loginName;
//		this.productId = productId;
//		this.productName = productName;
//		this.categoryName = categoryName;
//		this.price = price;
//		this.num = num;
//		this.fileName = fileName;
//	}
//
//
//	@Override
//	public String toString() {
//		return "Cart [id=" + id + ", loginName=" + loginName + ", productId=" + productId + ", productName="
//				+ productName + ", categoryName=" + categoryName + ", price=" + price + ", num=" + num + ", fileName="
//				+ fileName + "]";
//	}
	
	
	

}
