package com.zhu.easybuy.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhu.easybuy.pojo.Cart;
import com.zhu.easybuy.pojo.Product;
import com.zhu.easybuy.pojo.Result;
import com.zhu.easybuy.pojo.User;
import com.zhu.easybuy.service.CartService;
import com.zhu.easybuy.service.ProductService;
import com.zhu.easybuy.util.CommonUtils;

@RestController
@RequestMapping("/api/member/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	@Autowired
	private RedisTemplate redisTemplate;
	

	
	
	@RequestMapping("/addCart")
	public Result addCart(HttpServletRequest request,int id,int count) {
		//看看商品数量是否足够.
		int userId=CommonUtils.userOfLogin(request,redisTemplate).getId();
		Cart preCart=cartService.findCart(userId);//得到现在的cart
		Product product=productService.findProductById(id);
		int preCount;
		if(preCart==null||preCart.getMap()==null||preCart.getMap().get(id)==null) {//判断Cart或者CartItem是否为空
			preCount=0;
		}else {
			preCount=preCart.getMap().get(id).getCount();
		}
		if((preCount+count)>product.getStock()) {
			return new Result(false,"商品数量不够，加入购物车失败");
		}
		Cart cart=cartService.addCart(userId,id, count);
		return new Result(true,"加入购物车成功",cart);
	}
	
	
	@RequestMapping("/findCart")
	public Result findCart(HttpServletRequest request) {
	   int id=CommonUtils.userOfLogin(request,redisTemplate).getId();
	   Cart cart=cartService.findCart(id);
	   return new Result(true,"查询购物车成功",cart);
	}	
	
	

	@RequestMapping("/updateCart")
	public Result updateCart(HttpServletRequest request,int id,int count) {
	   int userId=CommonUtils.userOfLogin(request,redisTemplate).getId();
	   Product product=productService.findProductById(id);
	   if(count>product.getStock()) {
		   return new Result(false,"商品数量不足");
	   }
	   Cart cart=cartService.updateCart(userId,id,count);
	   return new Result(true,"更新购物车成功",cart);
	}	
	

	@RequestMapping("/deleteCart")
	public Result deleteCart(HttpServletRequest request,int id) {
	   int userId=CommonUtils.userOfLogin(request,redisTemplate).getId();
	   Cart cart=cartService.deleteCart(userId,id);
	   return new Result(true,"删除购物车成功",cart);
	}	
	
	

}
