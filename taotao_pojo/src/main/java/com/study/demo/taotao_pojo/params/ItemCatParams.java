package com.study.demo.taotao_pojo.params;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ItemCatParams extends BasePage implements Serializable {

    @NotNull(message = "parentId不能为空!!!")
    @Min(value = 0,message = "parentId不能小于0")
    private Integer parentId;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
