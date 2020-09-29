package com.zhu.easybuy.mapper;

import java.util.List;
import java.util.Locale.Category;

import org.apache.ibatis.annotations.Mapper;

import com.zhu.easybuy.pojo.Product;
import com.zhu.easybuy.pojo.ProductCategory;

@Mapper
public interface ProductCategoryMapper {
	
	/**
	 * 	private Integer id;//主键

		private String name;//名称
		private Integer parentId;//父级目录id
		private Integer type;//级别(1:一级 2：二级 3：三级)
		private String iconClass;//图标
	 */
	public List<ProductCategory> findAll();

	public ProductCategory findById(int id);
	
	public List<ProductCategory> findAllByType(int type);
	
	public List<ProductCategory> findAllByParentId(int parentId);
	
	public int  delete(int id);
	
	public List<Product> isCategoryHasPro();

	public ProductCategory findByName(String name);


	public void add(ProductCategory category);

	public List<ProductCategory> findAllNoType();

	

}
