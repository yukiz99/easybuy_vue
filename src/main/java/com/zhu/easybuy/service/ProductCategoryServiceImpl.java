package com.zhu.easybuy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhu.easybuy.mapper.ProductCategoryMapper;
import com.zhu.easybuy.mapper.ProductMapper;
import com.zhu.easybuy.pojo.Product;
import com.zhu.easybuy.pojo.ProductCategory;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

	@Autowired
	private ProductCategoryMapper categoryMapper;
	@Autowired
	private ProductMapper productMapper;
	
	
	//得到商品一级分类
	@Override
	public List<ProductCategory> getProductCategory() {
		return categoryMapper.findAllByParentId(0);
	}

	@Override
	public ProductCategory findCategoryById(int id) {
		return categoryMapper.findById(id);
	}

	//得到所有分类
	@Override
	public List<ProductCategory> findAllCategory() {

	    
		  List<ProductCategory> pcList1=categoryMapper.findAllByParentId(0);
		    for(ProductCategory pc1: pcList1) {
		    	List<ProductCategory> pcList2=categoryMapper.findAllByParentId(pc1.getId());
		    	pc1.setChildren(pcList2);
		    	for(ProductCategory pc2:pcList2) {
		    		List<ProductCategory> pcList3=categoryMapper.findAllByParentId(pc2.getId());
		    		pc2.setChildren(pcList3);
		    	}
		    }
	    return pcList1;
	}

	@Override
	public List<ProductCategory> findAllProductAndCategory(int pageNum, int pageSize) {
		
        List<ProductCategory> pcList1=categoryMapper.findAllByParentId(0);
	    for(ProductCategory pc1: pcList1) {
	    	List<ProductCategory> pcList2=categoryMapper.findAllByParentId(pc1.getId());
	    	//只给一级传输商品
	    	PageHelper.startPage(pageNum,pageSize);
	    	List<Product> productList=productMapper.findAllByCategoryId(pc1.getId());
	    	
	    	pc1.setChildren(pcList2);
	    	pc1.setList(productList);
	    	for(ProductCategory pc2:pcList2) {
	    		List<ProductCategory> pcList3=categoryMapper.findAllByParentId(pc2.getId());
	    		pc2.setChildren(pcList3);
	    	}
	    }
	    
	    return pcList1;
	}

	//删除时应判断该分类下是否有商品
	@Override
	public int deleteCategory(int id) {
	    List<Product> list=productMapper.findAllByCategoryId(id);
	    
	    if(list==null||list.size()==0) {
	    	return categoryMapper.delete(id);
	    }
	    return 0;
	}

	@Override
	public List<ProductCategory> findAllByParentId(int id) {
		return categoryMapper.findAllByParentId(id);
	}

	@Override
	public ProductCategory findCategoryByName(String name) {
		return categoryMapper.findByName(name);
		
	}

	@Override
	public void addCategory(ProductCategory category) {
		categoryMapper.add(category);
		
	}

	@Override
	public PageInfo<ProductCategory> findAllCategoryNoType(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<ProductCategory> list=categoryMapper.findAllNoType();
		PageInfo<ProductCategory> info=new PageInfo<ProductCategory>(list);
		return info;
	}	

}
