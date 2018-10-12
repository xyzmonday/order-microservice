package com.yff.order.object;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

public class OrderQo extends PageQo{
    private Long id;
    private String orderNo;
    private Long userId;
    private Long merchantId;
    private double amount;
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    private String operator;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modify;

    private List<OrderDetailQo> orderDetails = new ArrayList<>();

    public OrderQo() {
    }

    public void addOrderDetail(OrderDetailQo orderDetailQo){
        orderDetails.add(orderDetailQo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getModify() {
        return modify;
    }

    public void setModify(Date modify) {
        this.modify = modify;
    }

    public List<OrderDetailQo> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailQo> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
