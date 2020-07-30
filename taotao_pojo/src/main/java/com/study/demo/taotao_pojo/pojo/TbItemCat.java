package com.study.demo.taotao_pojo.pojo;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TbItemCat implements Serializable {

  private Integer id;
  @NotNull(message = "parentId不能为空!!!")
  @Min(value = 0,message = "parentId不能小于0")
  private Integer parentId;
  @NotBlank(message = "分类名称不能为空!!!")
  private String name;
  @NotNull(message = "模板ID不能为空!!!")
  @Min(value = 0,message = "模板ID不能小于0")
  private Integer typeId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }
}
