package com.zhu.easybuy.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhu.easybuy.pojo.User;

public interface UserService {
	
    public User login(String loginName,String password);
	
	public void register(User user);
	
	public PageInfo<User> allUser(int pageNum,int pageSize);
	
	public User findUserByLoginName(String loginName);

	public User findUserById(int id);
	
	public void updateUser(User user);
	
	public void deleteUser(int id);


}
