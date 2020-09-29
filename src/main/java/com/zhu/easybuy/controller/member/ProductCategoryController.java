package com.zhu.easybuy.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhu.easybuy.pojo.ProductCategory;
import com.zhu.easybuy.pojo.Result;
import com.zhu.easybuy.service.ProductCategoryService;

@RestController
@RequestMapping("/api/member/category")
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryService categoryService;
	
	//得到一级分类
	@RequestMapping("/getProductCategory")
	public Result getProductCategory(){
		return new Result(true,"查询成功",categoryService.getProductCategory());
	}
	
	
	@RequestMapping("/findCategoryById")
	public Result findCategoryById(int id) {
		ProductCategory c=categoryService.findCategoryById(id);
		return new Result(true,"查询成功",c);
	} 
	
	@RequestMapping("/findAllCategoryByParentId")
	public Result findAllCategoryByParentId(int id) {
		return new Result(true,"查询成功",categoryService.findAllByParentId(id));
	}
	
	@RequestMapping("/findAllCategory")
	public Result findAllCategory() {
		return new Result(true,"查询成功",categoryService.findAllCategory());
	}
	
	@RequestMapping("/findAllProductAndCategory")
	public Result findAllProductAndCategory(int pageNum,int pageSize) {
		return new Result(true,"查询成功",categoryService.findAllProductAndCategory(pageNum, pageSize));
	}
	
	@RequestMapping("/findAllCategoryNoType")
	public Result findAllCategoryNoType(int pageNum,int pageSize) {
		return new Result(true,"查询成功",categoryService.findAllCategoryNoType(pageNum,pageSize));
	}
	
	

}
