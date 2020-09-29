package com.zhu.easybuy.service;

import java.util.List;

import com.zhu.easybuy.pojo.Cart;

public interface CartService {
	
	
	Cart findCart(int id);
	
	Cart addCart(int id,int productId,int count);
	
	Cart updateCart(int id,int productId,int count);
	
	Cart deleteCart(int id,int productId);
}
