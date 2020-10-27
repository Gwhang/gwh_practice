package com.example.orderdemo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 物流信息
 */
public class Logistics implements Serializable {

    private int orderId;//订单id
    private String operation;//操作
    private String operator;//操作员
    //@JsonFormat(pattern = “yyyy-MM-dd HH:mm”,timezone = “GMT+8”)为时间类的json输出格式，供前端使用。
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date operationTime;//操作时间
    private String address;//地址
    private String details;//备注细节

    public Logistics(int orderId, String operation, String operator, Date operationTime, String address, String details) {
        this.orderId = orderId;
        this.operation = operation;
        this.operator = operator;
        this.operationTime = operationTime;
        this.address = address;
        this.details = details;
    }

    public Logistics(){}
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
