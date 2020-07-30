package com.study.demo.taotao_pojo.params;

import com.study.demo.taotao_pojo.pojo.GoodsAttributeItems;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class GoodsAttributeItemsParams implements Serializable {

    @NotNull(message = "商品ID不能为空")
    @Min(value = 0,message = "商品ID不能小于0")
    private Long id;

    private List<GoodsAttributeItems> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GoodsAttributeItems> getItems() {
        return items;
    }

    public void setItems(List<GoodsAttributeItems> items) {
        this.items = items;
    }
}
