package com.zhu.easybuy.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserAddress implements Serializable{


	private Integer id;//主键id
	
	private Integer userId;//用户主键
	private String address;//地址
	private Date createTime;//创建时间
	private Integer isDefault;//是否是默认地址（1:是 0否）
	private String remark;//备注
}
