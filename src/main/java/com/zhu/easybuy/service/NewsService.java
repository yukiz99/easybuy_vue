package com.zhu.easybuy.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhu.easybuy.pojo.News;

public interface NewsService{
	

	public PageInfo<News> allNews(int pageNum,int pageSize);

	public News findNewsById(int id);
}
