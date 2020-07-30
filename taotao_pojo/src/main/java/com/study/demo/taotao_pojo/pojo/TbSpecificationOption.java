package com.study.demo.taotao_pojo.pojo;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class TbSpecificationOption implements Serializable {

  private Integer id;
  @NotBlank(message = "规格选项名称不能为空!!!")
  private String optionName;
  private Integer specId;
  private Integer orders;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getSpecId() {
    return specId;
  }

  public void setSpecId(Integer specId) {
    this.specId = specId;
  }

  public Integer getOrders() {
    return orders;
  }

  public void setOrders(Integer orders) {
    this.orders = orders;
  }

  public String getOptionName() {
    return optionName;
  }

  public void setOptionName(String optionName) {
    this.optionName = optionName;
  }
}
