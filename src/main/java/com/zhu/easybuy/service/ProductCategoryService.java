package com.zhu.easybuy.service;

import java.util.List;
import java.util.Locale.Category;

import com.github.pagehelper.PageInfo;
import com.zhu.easybuy.pojo.ProductCategory;

public interface ProductCategoryService {
	
	//得到商品一级分类
	public List<ProductCategory> getProductCategory();
	
	public ProductCategory findCategoryById(int id); 
	
    public List<ProductCategory> findAllCategory();
    
    public List<ProductCategory> findAllProductAndCategory(int pageNum,int pageSize);
    
    public int deleteCategory(int id);

	public List<ProductCategory> findAllByParentId(int id);

	public ProductCategory findCategoryByName(String name);

	public void addCategory(ProductCategory category);

	public PageInfo<ProductCategory> findAllCategoryNoType(int pageNum,int pageSize);
    
    
}
