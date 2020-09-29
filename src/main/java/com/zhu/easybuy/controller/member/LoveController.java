package com.zhu.easybuy.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhu.easybuy.pojo.Cart;
import com.zhu.easybuy.pojo.Love;
import com.zhu.easybuy.pojo.Product;
import com.zhu.easybuy.pojo.Result;
import com.zhu.easybuy.service.CartService;
import com.zhu.easybuy.service.LoveService;
import com.zhu.easybuy.service.ProductService;
import com.zhu.easybuy.util.CommonUtils;

@RestController
@RequestMapping("/api/member/love")
public class LoveController {
	
	@Autowired
	private LoveService loveService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CartService cartService;
	@Autowired
	private RedisTemplate redisTemplate;
	
	@RequestMapping("/findAll")
	public Result findAll(HttpServletRequest request) {
		int userId=CommonUtils.userOfLogin(request, redisTemplate).getId();
		return new Result(true,"查询成功",loveService.findAll(userId));
	}
	
	//是否需要返回Love
	@RequestMapping("/addLove")
	public Result addLove(HttpServletRequest request,int id) {
		int userId=CommonUtils.userOfLogin(request, redisTemplate).getId();
		List<Love> list=(List<Love>) redisTemplate.opsForValue().get("Love_"+userId);
		if(list!=null) {
			for(Love love:list) {
				if(love.getProductId()==id) {
				  return new Result(false,"该商品已存在，无需再次收藏");	
				}			
			}
		}
		loveService.addLove(userId,id);
		return new Result(true,"加入收藏成功");
	}

	@RequestMapping("/deleteLove")
	public Result deleteLove(HttpServletRequest request,int id) {
		int userId=CommonUtils.userOfLogin(request, redisTemplate).getId();
		loveService.deleteLove(userId,id);
		return new Result(true,"删除收藏成功");
	}
	
	//假如该商品已在购物车，则无需数量增加
	@RequestMapping("/addCart")
	public Result addCart(HttpServletRequest request,int id) {
		int userId=CommonUtils.userOfLogin(request, redisTemplate).getId();
		Product product=productService.findProductById(id);
		Cart cart=(Cart) redisTemplate.opsForValue().get("Cart_"+userId);
		if(cart==null||cart.getMap().size()==0) {//购物车为空
			if(product.getStock()>0) {//购物车为空且有库存
				cartService.addCart(userId, id, 1);
				return new Result(true,"加入购物车成功");
			}else {
				return new Result(false,"库存不足");
			}
		}else {
			//购物车不为空但无该商品
			if(cart.getMap().get(id)==null) {
			   cartService.addCart(userId, id, 1);
			   return new Result(true,"加入购物车成功");
			}else {
				return new Result(false,"购物车已存在");
			}
		}		
	}
	
}
