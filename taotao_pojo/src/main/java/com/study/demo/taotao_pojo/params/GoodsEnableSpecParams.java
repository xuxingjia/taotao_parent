package com.study.demo.taotao_pojo.params;

import javax.validation.constraints.NotBlank;

public class GoodsEnableSpecParams extends BaseLongId {

    @NotBlank(message = "是否启用规格不能为空!!!")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
