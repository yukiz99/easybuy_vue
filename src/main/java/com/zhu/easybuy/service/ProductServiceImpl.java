package com.zhu.easybuy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhu.easybuy.mapper.ProductMapper;
import com.zhu.easybuy.pojo.Product;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public PageInfo<Product> findAllProductByCategoryId(int categoryId,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<Product> list=productMapper.findAllByCategoryId(categoryId);
		PageInfo<Product> info=new PageInfo<Product>(list);
		return info;
	}

	@Override
	public Product findProductById(int id) {
		return productMapper.findById(id);
	}

	@Override
	public PageInfo<Product> allProduct(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<Product> list=productMapper.allProduct();
		PageInfo<Product> info=new PageInfo<Product>(list);
		return info;
	}

	//逻辑删除
	@Override
	public void deleteProduct(int id) {
		productMapper.updateIsDelete(id);
	}

	@Override
	public void addProduct(Product product) {
		productMapper.add(product);
		
	}

	@Override
	public PageInfo<Product> search(String search, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<Product> list=productMapper.search(search);
		PageInfo<Product> info=new PageInfo<Product>(list);
		return info;
	}

	@Override
	public void updateProduct(Product product) {
		productMapper.update(product);
	}
	
	

}
