package com.zhu.easybuy.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhu.easybuy.pojo.Result;
import com.zhu.easybuy.service.NewsService;

@RestController
@RequestMapping("/api/member/news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping("/allNews")
	public Result allNews(int pageNum,int pageSize){
		return new Result(true,"查询成功",newsService.allNews(pageNum,pageSize));
	}
	
	@RequestMapping("/findNewsById")
	public Result findNewsById(int id) {
		return new Result(true,"查询成功",newsService.findNewsById(id));
	}

}
