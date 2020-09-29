package com.zhu.easybuy.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductCategory implements Serializable{
	private Integer id;//主键

	private String name;//名称
	private Integer parentId;//父级目录id
	private Integer type;//级别(1:一级 2：二级 3：三级)
	private String iconClass;//图标
	
	private List<ProductCategory> children;
	private List<Product> list;
}
