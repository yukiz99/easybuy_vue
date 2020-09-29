package com.zhu.easybuy.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhu.easybuy.pojo.Product;

public interface ProductService {
	
	public PageInfo<Product> findAllProductByCategoryId(int categoryId,int pageNum,int pageSize);
	
    public Product findProductById(int id);

	public PageInfo<Product> allProduct(int pageNum,int pageSize);

	public void deleteProduct(int id);

	public void addProduct(Product product);

	public PageInfo<Product> search(String search, int pageNum, int pageSize);

	public void updateProduct(Product product);
	
	

}
