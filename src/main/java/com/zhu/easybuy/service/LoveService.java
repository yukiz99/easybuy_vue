package com.zhu.easybuy.service;

import java.util.List;


import com.zhu.easybuy.pojo.Love;

public interface LoveService {
	
	public List<Love>  findAll(int userId);

	public void addLove(int userId,int productId);

	public void deleteLove(int userId, int productId);
	

}
