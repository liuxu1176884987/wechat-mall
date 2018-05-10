package com.lntu.enums;

public enum OrderStatusEnum {
    ORDER_CANCEL(0,"订单取消"),
    ORDER_NOT_PAY(10,"未付款"),
    WAIT_SHIP(20,"待发货"),
    WAIT_RECEIPT(30,"待收货"),
    WAIT_EVALUATION(40,"待评价"),
    ORDER_FINISH(50,"交易完成"),
    ORDER_CLOSE(60,"交易关闭")
    ;
    private Integer status;
    private String msg;

    OrderStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
