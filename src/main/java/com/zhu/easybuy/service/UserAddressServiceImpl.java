package com.zhu.easybuy.service;

import java.util.Date;
import java.util.List;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhu.easybuy.mapper.UserAddressMapper;
import com.zhu.easybuy.pojo.UserAddress;

@Service
public class UserAddressServiceImpl implements UserAddressService{
	
	@Autowired
	private UserAddressMapper addressMapper;

	@Override
	public List<UserAddress> findAllByUserId(int userId) {
		return addressMapper.findAllByUserId(userId);
	}


	@Override
	public UserAddress findAddressById(int id) {
		return addressMapper.findAllById(id);
	}
	
	
	@Override
	public void addAddress(UserAddress address) {
		address.setCreateTime(new Date());
		address.setIsDefault(0);
//		if(addressMapper.findAllByUserId(address.getUserId())!=null) {
//			address.setIsDefault(0);
//		}else {
//			address.setIsDefault(1);//第一个地址默认是默认地址
//		}
		addressMapper.add(address);
		
	}

	@Override
	public void updateAddress(UserAddress address) {
		addressMapper.update(address);
		
	}

	@Override
	public void deleteAddress(int id) {
		addressMapper.delete(id);
		
	}


	//开启事务
	@Override
	public void setDefault(int id) {
		
		addressMapper.setDefault1();
		addressMapper.setDefault2(id);
		
	}

	
	

}
