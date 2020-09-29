package com.zhu.easybuy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zhu.easybuy.mapper.ProductMapper;
import com.zhu.easybuy.pojo.Cart;
import com.zhu.easybuy.pojo.CartItem;
import com.zhu.easybuy.pojo.Product;
import com.zhu.easybuy.pojo.User;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private ProductMapper productMapper;

	/**
	 * 查询购物车
	 */
	@Override
	public Cart findCart(int id) {
		Cart cart=(Cart) redisTemplate.opsForValue().get("Cart_"+id);
		return cart;
	}

	/**
	 * 添加购物车
	 */
	@Override
	public Cart addCart(int id, int productId, int count) {
		
		Cart cart=(Cart) redisTemplate.opsForValue().get("Cart_"+id);
		Product product=productMapper.findById(productId);
		//购物车是否第一次开始
		if(cart==null) {
			cart=new Cart();//初始化购物车
			Map<Integer, CartItem> map=new HashMap<Integer, CartItem>();//初始化map
			CartItem item=new CartItem(productId,product.getName(),product.getFileName(),product.getPrice(),count);
			map.put(productId,item);
			cart.setMap(map);
			cart.setTotal(count*product.getPrice());
		}else {
			Map<Integer, CartItem> map=cart.getMap();//初始化map
			if(map.containsKey(productId)) {
				CartItem item=map.get(productId);
				item.setCount(item.getCount()+count);
				map.put(productId,item);
			}else {
				CartItem item=new CartItem(productId,product.getName(),product.getFileName(),product.getPrice(),count);
				map.put(productId,item);
			}
			cart.setMap(map);
			cart.setTotal(count*product.getPrice()+cart.getTotal());
		}
		redisTemplate.opsForValue().set("Cart_"+id,cart);//覆盖redis中的cart
		return cart;
	}

	@Override
	public Cart updateCart(int id, int productId, int count) {
		//修改数量
		Cart cart=(Cart) redisTemplate.opsForValue().get("Cart_"+id);
		CartItem item=cart.getMap().get(productId);
		float total=cart.getTotal()-(item.getPrice()*(item.getCount()-count));
		cart.setTotal(total);
		item.setCount(count);
		cart.getMap().put(productId,item);//这里直接就是改的cart中的map值
		redisTemplate.opsForValue().set("Cart_"+id,cart);
		return cart;
	}

	/**
	 * 删除购物车
	 */
	@Override
	public Cart deleteCart(int id, int productId) {
		Cart cart=(Cart) redisTemplate.opsForValue().get("Cart_"+id);
		CartItem item=cart.getMap().get(productId);
		cart.setTotal(cart.getTotal() - item.getCount()*item.getPrice());
		cart.getMap().remove(productId);
		
		redisTemplate.opsForValue().set("Cart_"+id,cart);
		return cart;
	}


	
	

}
