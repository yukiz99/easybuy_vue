package com.zhu.easybuy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zhu.easybuy.mapper.ProductMapper;
import com.zhu.easybuy.pojo.Love;
import com.zhu.easybuy.pojo.Product;

@Service
public class LoveServiceImpl implements LoveService{

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private RedisTemplate redisTemplate;
	
	private static final int LoveSize=6;//设置默认收藏个数为6

	@Override
	public List<Love> findAll(int userId) {
		 return (List<Love>) redisTemplate.opsForValue().get("Love_"+userId);
	}

	/**
		private int productId;
		private String productName;
		private String fileName;
		private float price;
	 */
	@Override
	public void addLove(int userId,int productId) {
		
		List<Love> list=(List<Love>) redisTemplate.opsForValue().get("Love_"+userId);
		if(list==null) {
			list=new ArrayList<Love>();
		}
		Product product=productMapper.findById(productId);
		Love love=new Love();
		love.setProductId(productId);
		love.setProductName(product.getName());
		love.setFileName(product.getFileName());
		love.setPrice(product.getPrice());
		//判断收藏夹商品目前个数,前进前出
		if(list.size()==LoveSize) {
			list.remove(0);
			list.add(love);
		}else {
			list.add(love);
		}
		redisTemplate.opsForValue().set("Love_"+userId,list);
	}

	@Override
	public void deleteLove(int userId, int productId) {
		List<Love> list=(List<Love>) redisTemplate.opsForValue().get("Love_"+userId);
		for(Love love:list) {
			if(love.getProductId()==productId) {
				list.remove(love);
				break;
			}
		}
		redisTemplate.opsForValue().set("Love_"+userId,list);
		
	}
	
	
	
	
}
