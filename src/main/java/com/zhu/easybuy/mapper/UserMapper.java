package com.zhu.easybuy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhu.easybuy.pojo.User;

@Mapper
public interface UserMapper {
//	private Integer id;//主键
//
//	private String loginName;//登录名
//	private String userName;//用户名
//	private String password;//密码
//	private Integer sex;//性别(1:男 0：女)
//	private String identityCode;//身份证号
//	private String email;//邮箱
//	private String mobile;//手机
//	private Integer type;//类型（1：后台 0:前台）
//	

	public List<User> findAll();
	
	public User findByLoginName(String loginName);
	
	public User findUserById(int id);
	
	public void add(User user);
	
	public void update(User user);
	
	public void delete(int id);


}
