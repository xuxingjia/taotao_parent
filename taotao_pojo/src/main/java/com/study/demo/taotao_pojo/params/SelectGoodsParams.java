package com.study.demo.taotao_pojo.params;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class SelectGoodsParams extends BasePage implements Serializable {

    private String queryCondition;

    private String auditStatus;
    private String sellerId;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(String queryCondition) {
        this.queryCondition = queryCondition;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
}
