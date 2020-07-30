package com.study.demo.taotao_pojo.result;

import com.study.demo.taotao_pojo.pojo.TbOrderItem;

import java.io.Serializable;
import java.util.List;

public class ShoppongCartResult implements Serializable {

    private String sellerId;

    private String sellerName;

    private List<TbOrderItem> orderItems;


    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
