package com.zhu.easybuy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhu.easybuy.mapper.NewsMapper;
import com.zhu.easybuy.pojo.News;

@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	private NewsMapper newsMapper;

	@Override
	public PageInfo<News> allNews(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<News> list=newsMapper.allNews();
		PageInfo<News> info=new PageInfo<News>(list);
		return info;
	}

	@Override
	public News findNewsById(int id) {
		return newsMapper.findById(id);
	}
		

}
