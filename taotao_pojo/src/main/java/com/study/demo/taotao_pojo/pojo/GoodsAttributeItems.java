package com.study.demo.taotao_pojo.pojo;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class GoodsAttributeItems implements Serializable {

    @NotBlank(message = "扩展属性值不能为空!!!")
    private String value;

    @NotBlank(message = "扩展属性不能为空!!!")
    private String text;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
