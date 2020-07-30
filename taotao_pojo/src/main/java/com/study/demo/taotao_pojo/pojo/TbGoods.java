package com.study.demo.taotao_pojo.pojo;


import java.io.Serializable;
import java.math.BigDecimal;

public class TbGoods implements Serializable {

  private Long id;
  //商家ID
  private String sellerId;
  //SPU名
  private String goodsName;
  //默认SKU
  private Integer defaultItemId;
  //状态 0:待审核 1:审核通过 2:审核驳回
  private String auditStatus;
  //是否上架 1:上架  0:未上架
  private String isMarketable;
  //品牌
  private Integer brandId;
  //副标题
  private String caption;
  //一级类目
  private Integer category1Id;
  //二级类目
  private Integer category2Id;
  //三级类目
  private Integer category3Id;
  //小图
  private String smallPic;
  //商城价
  private BigDecimal price;
  //分类模板ID
  private Integer typeTemplateId;
  //是否启用规格 0:启动 1:未启用
  private String isEnableSpec;
  //是否删除 0:删除 1:未删除
  private String isDelete;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public Integer getDefaultItemId() {
    return defaultItemId;
  }

  public void setDefaultItemId(Integer defaultItemId) {
    this.defaultItemId = defaultItemId;
  }

  public String getAuditStatus() {
    return auditStatus;
  }

  public void setAuditStatus(String auditStatus) {
    this.auditStatus = auditStatus;
  }

  public String getIsMarketable() {
    return isMarketable;
  }

  public void setIsMarketable(String isMarketable) {
    this.isMarketable = isMarketable;
  }

  public Integer getBrandId() {
    return brandId;
  }

  public void setBrandId(Integer brandId) {
    this.brandId = brandId;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public Integer getCategory1Id() {
    return category1Id;
  }

  public void setCategory1Id(Integer category1Id) {
    this.category1Id = category1Id;
  }

  public Integer getCategory2Id() {
    return category2Id;
  }

  public void setCategory2Id(Integer category2Id) {
    this.category2Id = category2Id;
  }

  public Integer getCategory3Id() {
    return category3Id;
  }

  public void setCategory3Id(Integer category3Id) {
    this.category3Id = category3Id;
  }

  public String getSmallPic() {
    return smallPic;
  }

  public void setSmallPic(String smallPic) {
    this.smallPic = smallPic;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getTypeTemplateId() {
    return typeTemplateId;
  }

  public void setTypeTemplateId(Integer typeTemplateId) {
    this.typeTemplateId = typeTemplateId;
  }

  public String getIsEnableSpec() {
    return isEnableSpec;
  }

  public void setIsEnableSpec(String isEnableSpec) {
    this.isEnableSpec = isEnableSpec;
  }

  public String getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(String isDelete) {
    this.isDelete = isDelete;
  }
}
