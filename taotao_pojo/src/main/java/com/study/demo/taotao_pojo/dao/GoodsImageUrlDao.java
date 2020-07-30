package com.study.demo.taotao_pojo.dao;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class GoodsImageUrlDao implements Serializable {


    private Long id;


    private String url;

    private String color;

    public GoodsImageUrlDao(@NotBlank(message = "商品图片Url不能为空") String url, @NotBlank(message = "商品图片颜色不能为空!!!") String color) {
        this.url = url;
        this.color = color;
    }

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
