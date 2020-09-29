package com.zhu.easybuy.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartItem implements Serializable{
	
	private int productId;
	private String productName;
	private String fileName;
	private float price;
	private int count;
	
	public CartItem() {
		super();
	}
	
	public CartItem(int productId, String productName,String fileName, float price, int count) {
		super();
		this.productId = productId;
		this.productName=productName;
		this.fileName = fileName;
		this.price = price;
		this.count = count;
	}

	@Override
	public String toString() {
		return "CartItem [productId=" + productId + ", productName=" + productName + ", fileName=" + fileName
				+ ", price=" + price + ", count=" + count + "]";
	}
	
	

}
