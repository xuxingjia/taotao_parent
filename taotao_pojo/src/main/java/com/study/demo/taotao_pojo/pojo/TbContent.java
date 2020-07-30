package com.study.demo.taotao_pojo.pojo;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
public class TbContent implements Serializable{

  private long id;
  @NotNull(message = "广告分类ID不能为空!!!")
  @Min(message = "广告分类id不能小于0",value = 1)
  private long categoryId;
  @NotBlank(message = "广告标题不能为空!!!")
  private String title;
  private String url;
  @NotBlank(message = "广告图片不能为空!!!")
  private String pic;
  @NotBlank(message = "广告是否有效不能为空!!!")
  private String status;
  @NotNull(message = "广告排序不能为空!!!")
  @Min(value = 0,message = "排序最小值为0!!!")
  private long sortOrder;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public long getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(long sortOrder) {
    this.sortOrder = sortOrder;
  }

}
