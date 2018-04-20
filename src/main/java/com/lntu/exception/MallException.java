package com.lntu.exception;

import com.lntu.enums.MallStatusEnum;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * 自定义异常
 * Created by Administrator on 2018/3/27.
 */
@Data
@ToString
public class MallException extends RuntimeException {

    private Integer state;

    private String msg;

    private String logKey;

    private Map<String,Object> params;

    public MallException(Integer state, String msg) {
        super(msg);
        this.state = state;
    }

    public MallException(MallStatusEnum mallStatusEnum, Map params){
        super(mallStatusEnum.getMsg());
        this.state = mallStatusEnum.getState();
        this.params = params;
    }

    public MallException(MallStatusEnum mallStatusEnum){
        super(mallStatusEnum.getMsg());
        this.state = mallStatusEnum.getState();
    }

}