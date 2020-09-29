package com.zhu.easybuy.pojo;


import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable{

	
	private Integer id;//主键

	private String loginName;//登录名
	private String userName;//用户名
	private String password;//密码
	private Integer sex;//性别(1:男 0：女)
	private String identityCode;//身份证号
	private String email;//邮箱
	private String mobile;//手机
	private Integer type;//类型（1：后台 0:前台）
	
}
