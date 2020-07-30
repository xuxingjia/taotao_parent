package com.study.demo.taotao_pojo.params;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class AlterSellerStatusParams extends BaseStrId implements Serializable {

    @NotBlank(message = "审核状态不能为空!!!")
    private String sellerStatus;

    public String getSellerStatus() {
        return sellerStatus;
    }

    public void setSellerStatus(String sellerStatus) {
        this.sellerStatus = sellerStatus;
    }
}
