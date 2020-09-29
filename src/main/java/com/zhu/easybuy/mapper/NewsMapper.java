package com.zhu.easybuy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhu.easybuy.pojo.News;

@Mapper
public interface NewsMapper {
	

	public List<News> allNews();

	public News findById(int id);

}
