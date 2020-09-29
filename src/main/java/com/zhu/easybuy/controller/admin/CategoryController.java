package com.zhu.easybuy.controller.admin;

import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhu.easybuy.pojo.ProductCategory;
import com.zhu.easybuy.pojo.Result;
import com.zhu.easybuy.service.ProductCategoryService;

@RestController("AdminCategoryController")
@RequestMapping("/api/admin/category")
public class CategoryController {
	
	@Autowired
	private ProductCategoryService categoryService;
	
	@RequestMapping("/deleteCategory")
	public Result deleteCategory(int id) {
		int flag=categoryService.deleteCategory(id);
		if(flag>0) {
			return new Result(true,"删除成功");
		}
		return new Result(false,"删除失败");
	}
	
	@RequestMapping("/addCategory")
	public Result updateCategory(ProductCategory category) {
		ProductCategory c=categoryService.findCategoryByName(category.getName());
		if(c==null) {
			categoryService.addCategory(category);
			return new Result(true,"添加成功");
		}else{
			return new Result(false,"添加失败，该分类已存在");
		}
	}

}
