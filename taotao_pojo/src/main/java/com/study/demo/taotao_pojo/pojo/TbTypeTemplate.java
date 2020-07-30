package com.study.demo.taotao_pojo.pojo;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class TbTypeTemplate implements Serializable {

  private Integer id;
  @NotBlank(message = "模板名称不能为空!!!")
  private String name;
  private String specIds;
  private String brandIds;
  private String customAttributeItems;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getSpecIds() {
    return specIds;
  }

  public void setSpecIds(String specIds) {
    this.specIds = specIds;
  }


  public String getBrandIds() {
    return brandIds;
  }

  public void setBrandIds(String brandIds) {
    this.brandIds = brandIds;
  }


  public String getCustomAttributeItems() {
    return customAttributeItems;
  }

  public void setCustomAttributeItems(String customAttributeItems) {
    this.customAttributeItems = customAttributeItems;
  }

}
