package com.zhu.easybuy.pojo;


import java.io.Serializable;

import lombok.Data;
@Data
public class News implements Serializable{

	private Integer id;//主键

	private String title;//标题
	private String content;//内容
	private String createTime;//创建时间
}
