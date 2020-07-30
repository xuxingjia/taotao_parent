package com.study.demo.taotao_pojo.pojo;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
public class TbGoodsImage implements Serializable{
  @NotBlank(message = "商品图片颜色不能为空!!!")
  private String url;
  @NotBlank(message = "商品图片Url不能为空")
  private String color;
  private long id;
  @NotNull(message = "商品id不能为空")
  @Min(value = 0,message = "商品ID不能小于0")
  private long goodsId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(long goodsId) {
    this.goodsId = goodsId;
  }

}
