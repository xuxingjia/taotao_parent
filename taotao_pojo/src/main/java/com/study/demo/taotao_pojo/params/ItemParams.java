package com.study.demo.taotao_pojo.params;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ItemParams implements Serializable {

    @NotNull(message = "商品ID异常")
    private Integer[] ids;
    @NotBlank(message = "商品状态异常")
    private String status;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
