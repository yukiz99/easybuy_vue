package com.zhu.easybuy.pojo;

import lombok.Data;

/**
 * 控制器类返回结果
 */
@Data
public class Result {

    private boolean flag;//是否成功    
    private String msg;//返回信息    
    private Object data;// 返回数据

    public Result(){}
    public Result(boolean flag, String msg, Object data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    public Result(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }
}
