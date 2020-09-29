package com.zhu.easybuy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhu.easybuy.pojo.UserAddress;

@Mapper
public interface UserAddressMapper {
	
	public List<UserAddress> findAllByUserId(int userId);
	

	public UserAddress findAllById(int id);
	
	public void add(UserAddress address);
	
	public void update(UserAddress address);
	
	public void delete(int id);

	public void deleteByUser(int userId);

	public void setDefault1();
	public void setDefault2(int id);

	
	

}
