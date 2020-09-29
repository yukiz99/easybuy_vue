package com.zhu.easybuy.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhu.easybuy.pojo.Result;
import com.zhu.easybuy.service.ProductService;

@RestController
@RequestMapping("/api/member/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/findAllProductByCategoryId")
	public Result findAllProductByCategoryId(int categoryId,@RequestParam(defaultValue="1")int pageNum,int pageSize) {
		return new Result(true,"查询成功",productService.findAllProductByCategoryId(categoryId,pageNum,pageSize));
	}
	
	@RequestMapping("/findProductById")
	public Result findProductById(int id) {
		return new Result(true,"查询成功",productService.findProductById(id));
	}
	
	@RequestMapping("/search")
	public Result search(String search,@RequestParam(defaultValue="1")int pageNum,int pageSize) {
		return new Result(true,"搜索成功",productService.search(search,pageNum,pageSize));
	}
	

}
