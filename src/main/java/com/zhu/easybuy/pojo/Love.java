package com.zhu.easybuy.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Love implements Serializable{
	
//	private int userId;
	private int productId;
	private String productName;
	private String fileName;
	private float price;
}
