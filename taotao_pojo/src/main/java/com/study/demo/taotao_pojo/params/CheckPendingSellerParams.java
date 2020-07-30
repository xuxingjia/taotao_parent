package com.study.demo.taotao_pojo.params;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class CheckPendingSellerParams extends BasePage implements Serializable {

    @NotBlank(message = "审核状态不能为空!!!")
    private String checkStatus;

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }
}
