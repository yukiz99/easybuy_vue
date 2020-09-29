package com.zhu.easybuy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhu.easybuy.mapper.OrderDetailMapper;
import com.zhu.easybuy.mapper.OrderMapper;
import com.zhu.easybuy.mapper.ProductMapper;
import com.zhu.easybuy.pojo.Cart;
import com.zhu.easybuy.pojo.CartItem;
import com.zhu.easybuy.pojo.Order;
import com.zhu.easybuy.pojo.OrderDetail;
import com.zhu.easybuy.pojo.Product;
import com.zhu.easybuy.pojo.User;
import com.zhu.easybuy.pojo.UserAddress;
import com.zhu.easybuy.util.CommonUtils;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	RedisTemplate redisTemplate;
	/**
	 * 	
	private int productId;
	private String productName;
	private String fileName;
	private float price;
	private int count;
	--------------------------------
	private Integer id;//主键
	
	private Integer userId;//用户主键
	private String loginName;//
	private String userAddress;//用户地址
	private Date createTime;//创建时间
	private Float cost;//总消费
	private String serialNumber;//订单号
	
	private List<OrderDetail> list;
	---------------------------------
	private Integer id;//主键
	
	private Integer orderId;//订单主键
	private Integer productId;//商品主键
	private Integer quantity;//数量
	private Float cost;//消费
	
	private Product product;
	 * @param cart
	 */
	
	//确认订单、开启事务:库存应该减少
	public int confirmOrder(Cart cart,User user,String address) {
		Order order=new Order();
		order.setUserId(user.getId());
		order.setLoginName(user.getLoginName());
		order.setUserAddress(address);
		order.setCreateTime(new Date());
        order.setCost(cart.getTotal());
		order.setSerialNumber(CommonUtils.uuid()+"");
		orderMapper.add(order);//自增返回id
		int orderId=order.getId();
		
		for(Map.Entry<Integer, CartItem> me : cart.getMap().entrySet()) {
			OrderDetail detail=new OrderDetail();
			CartItem item=me.getValue();
			detail.setOrderId(orderId);
			detail.setProductId(item.getProductId());
			detail.setQuantity(item.getCount());
			detail.setCost(item.getCount()*item.getPrice());	
			orderDetailMapper.add(detail);
			//商品减少库存
			Product product=productMapper.findById(detail.getProductId());
			product.setStock(product.getStock()-detail.getQuantity());//重新设置库存
			productMapper.update(product);
			
		}
		//清空购物车
		redisTemplate.delete("Cart_"+user.getId());
		return orderId;
	}
	
	
	//我的订单:order+orderDetail(包含product)
	public PageInfo<Order> myOrder(int userId,int pageNum,int pageSize){
		PageHelper.startPage(pageNum,pageSize);
		List<Order> orderList=orderMapper.findAllByUser(userId);
		for(Order order:orderList) {
			List<OrderDetail> detailList=orderDetailMapper.findAllByOrderId(order.getId());
			for(OrderDetail detail:detailList) {
				detail.setProduct(productMapper.findById(detail.getProductId()));//给orderDetail添加product
			}
			order.setList(detailList);
		}
		PageInfo<Order> info=new PageInfo<Order>(orderList);
		return info;
	}
	
	//全部订单
	public PageInfo<Order> AllOrder(int pageNum,int pageSize){
		PageHelper.startPage(pageNum,pageSize);
		List<Order> orderList=orderMapper.findAll();
		for(Order order:orderList) {
			List<OrderDetail> detailList=orderDetailMapper.findAllByOrderId(order.getId());
			for(OrderDetail detail:detailList) {
				detail.setProduct(productMapper.findById(detail.getProductId()));//给orderDetail添加product
			}
			order.setList(detailList);
		}
		PageInfo<Order> info=new PageInfo<Order>(orderList);
		return info;
	}


	@Override
	public Order findOrderById(int id) {
		Order order=orderMapper.findById(id);
		List<OrderDetail> list=orderDetailMapper.findAllByOrderId(order.getId());
		order.setList(list);
		return order;
	}

}
