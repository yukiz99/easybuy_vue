package com.zhu.easybuy.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhu.easybuy.mapper.OrderDetailMapper;
import com.zhu.easybuy.mapper.OrderMapper;
import com.zhu.easybuy.mapper.UserAddressMapper;
import com.zhu.easybuy.mapper.UserMapper;
import com.zhu.easybuy.pojo.Order;
import com.zhu.easybuy.pojo.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderDetailMapper detailMapper;
	@Autowired
	private UserAddressMapper addressMapper;
	
	@Override
	public User login(String loginName,String password) {
		User user=userMapper.findByLoginName(loginName);
		if(user!=null) {
			if(user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public void register(User user) {
		String password=user.getPassword();
		user.setPassword(DigestUtils.md5Hex(password));//给密码加密
		user.setType(0);//设置成前台
		userMapper.add(user);		
	}

	@Override
	public PageInfo<User> allUser(int pageNum,int pageSize){
		PageHelper.startPage(pageNum,pageSize);
		List<User> list=userMapper.findAll();
		PageInfo<User> info=new PageInfo<User>(list);
		return info;
	}
	
	@Override
	public User findUserByLoginName(String loginName) {
		return userMapper.findByLoginName(loginName);
	}
	
	@Override
	public User findUserById(int id) {
		return userMapper.findUserById(id);
	}

	@Override
	public void updateUser(User user) {
		userMapper.update(user);
		
	}

	//删除时应该将order、orderDetail、userAddress一柄删除；
	@Override
	public void deleteUser(int id) {
		List<Order> orderList=orderMapper.findAllByUser(id);
		for(Order order:orderList) {
			detailMapper.deleteByOrder(order.getId());
		}  
		orderMapper.deleteByUser(id);
		addressMapper.deleteByUser(id);
		userMapper.delete(id);
	}
	
	

}
