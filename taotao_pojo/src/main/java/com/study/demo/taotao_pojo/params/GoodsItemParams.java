package com.study.demo.taotao_pojo.params;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GoodsItemParams implements Serializable {

    @NotNull(message = "价格不能为空")
    @Min(value = 0,message = "价格不能小于0")
    private BigDecimal price;
    @NotNull(message = "库存不能为空")
    @Min(value = 0,message = "库存不能小于0")
    private Integer num;
    @NotNull(message = "是否默认不能为空")
    //0: 为默认 1:为不默认
    @NotBlank(message = "默认状态不能为空")
    private String isDefault;
    //1正常 2下架 3删除
    @NotNull(message = "商品状态不能为空")
    private Integer status;
    private List<GoodsSpecValue> values;
    @NotBlank(message = "商品ID不能为空")
//    @Min(value = 0,message = "商品ID不能小于0")
    private String id;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<GoodsSpecValue> getValues() {
        return values;
    }

    public void setValues(List<GoodsSpecValue> values) {
        this.values = values;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
