package com.study.demo.taotao_pojo.pojo;


import java.io.Serializable;
import java.math.BigDecimal;

public class TbOrderItem implements Serializable{

  private long id;
  private long itemId;
  private long goodsId;
  private long orderId;
  private String title;
  private BigDecimal price;
  private long num;
  private BigDecimal totalFee;
  private String picPath;
  private String sellerId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getItemId() {
    return itemId;
  }

  public void setItemId(long itemId) {
    this.itemId = itemId;
  }


  public long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(long goodsId) {
    this.goodsId = goodsId;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public long getNum() {
    return num;
  }

  public void setNum(long num) {
    this.num = num;
  }


  public BigDecimal getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(BigDecimal totalFee) {
    this.totalFee = totalFee;
  }

  public String getPicPath() {
    return picPath;
  }

  public void setPicPath(String picPath) {
    this.picPath = picPath;
  }


  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }

}
