package com.lntu.enums;

/**
 * 静态变量
 * Created by Administrator on 2018/3/29.
 */
public enum MallStatusEnum {
    DATA_RESULT_SUCCESS(200,"数据返回成功"),
    PRO_SELECT_FAIL(5001,"商品查询失败"),
    PRODUCT_SC_FAIL(5002,"商品收藏失败"),
    CLIENT_CHECK_FAIL(5003,"客户端验证失败"),
    ADD_SHOPPING_CAR_FAIL(5004,"添加购物车商品失败"),
    SHOPPING_SELECT_FAIL(5005,"购物车查询失败"),
    CANCEL_ORDER_FAIL(5006,"取消订单失败"),
    GET_USER_INFO_FAIL(4001,"获取用户信息失败")
    ;
    private Integer state;
    private String msg;

    MallStatusEnum(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
