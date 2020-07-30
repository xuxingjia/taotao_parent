package com.study.demo.taotao_pojo.pojo;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TbGoodsDesc implements Serializable {

  @NotNull(message = "商品ID不能为空!!!")
  @Min(value = 0,message = "商品ID不能小于0!!!")
  private Long goodsId;
  //描述
  private String introduction;
  //规格结果集，所有规格，包含isSelected
  private String specificationItems;
  //自定义属性（参数结果）
  private String customAttributeItems;
  //图片
  private String itemImages;
  //包装列表
  private String packageList;
  //售后服务
  private String saleService;


  public Long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Long goodsId) {
    this.goodsId = goodsId;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }


  public String getSpecificationItems() {
    return specificationItems;
  }

  public void setSpecificationItems(String specificationItems) {
    this.specificationItems = specificationItems;
  }


  public String getCustomAttributeItems() {
    return customAttributeItems;
  }

  public void setCustomAttributeItems(String customAttributeItems) {
    this.customAttributeItems = customAttributeItems;
  }


  public String getItemImages() {
    return itemImages;
  }

  public void setItemImages(String itemImages) {
    this.itemImages = itemImages;
  }


  public String getPackageList() {
    return packageList;
  }

  public void setPackageList(String packageList) {
    this.packageList = packageList;
  }


  public String getSaleService() {
    return saleService;
  }

  public void setSaleService(String saleService) {
    this.saleService = saleService;
  }

}
