package com.study.demo.taotao_pojo.params;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DeleteGoodsImageParams implements Serializable {

    @NotNull(message = "商品ID不能为空")
    @Min(value = 0,message = "商品ID不能小于0")
    private Long id;

    @NotBlank(message = "商品颜色不能为空")
    private String color;

    @NotBlank(message = "图片UEL不能为空")
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
