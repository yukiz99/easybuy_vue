package com.zhu.easybuy.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Product implements Serializable{

	private Integer id;//主键
	
	private String name;//名称
	private String description;//描述
	private Float price;//价格
	private Integer stock;//库存
	private Integer categoryLevel1Id;//分类1
	private Integer categoryLevel2Id;//分类2
	private Integer categoryLevel3Id;//分类3
	private String fileName;//文件名称
	private Integer isDelete;//是否删除(1：删除 0：未删除)
	
	
}
