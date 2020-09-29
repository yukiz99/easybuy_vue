package com.zhu.easybuy.service;

import java.util.List;


import com.zhu.easybuy.pojo.UserAddress;

public interface UserAddressService {
	
	public List<UserAddress> findAllByUserId(int userId);
	
	public UserAddress findAddressById(int id);
	
	public void addAddress(UserAddress address);
	
	public void updateAddress(UserAddress address);
	
	public void deleteAddress(int id);

	public void setDefault(int id);


}
