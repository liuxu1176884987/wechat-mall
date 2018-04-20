package com.lntu.common;

import com.lntu.enums.MallStatusEnum;
import lombok.Data;
import lombok.ToString;

/**
 * Json数据返回格式
 * Created by Administrator on 2018/3/26.
 */
@Data
@ToString
public class JsonData{

    //返回状态
    private Integer state;

    //返回消息
    private String msg;

    //返回错误消息
    private String err;

    //返回数据
    private Object data;

    public JsonData(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public JsonData(Integer state, String msg, Object data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public JsonData(Integer state, String msg, String err) {
        this.state = state;
        this.msg = msg;
        this.err = err;
        this.data = data;
    }

    public JsonData(MallStatusEnum mallStatusEnum, Object data){
        this.state = mallStatusEnum.getState();
        this.msg = mallStatusEnum.getMsg();
        this.data = data;
    }

    public static JsonData fail(Integer state,String msg){
        return new JsonData(state,msg);
    }

    public static JsonData success(Integer state,String msg,Object data){
        return new JsonData(state,msg,data);
    }

}
