package com.zhu.easybuy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhu.easybuy.pojo.Product;


@Mapper
public interface ProductMapper {
	
    public List<Product> findAllByCategoryId(int categoryId);
    
    public Product findById(int id);

	public List<Product> allProduct();

	public void updateIsDelete(int id);//逻辑删除

	public void update(Product product);

	public void add(Product product);

	public List<Product> search(@Param("search")String search); 
}
