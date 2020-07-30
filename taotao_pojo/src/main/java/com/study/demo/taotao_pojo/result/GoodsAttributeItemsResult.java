package com.study.demo.taotao_pojo.result;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class GoodsAttributeItemsResult implements Serializable {

    @NotBlank(message = "扩展属性不能为空!!!")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
