package com.study.demo.taotao_pojo.params;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BasePage implements Serializable {
    @NotNull(message = "page不能为空!!")
    @Min(value = 1,message = "页数不能小于1!!!")
    private int page;
    @NotNull(message = "pageSize不能为空!!")
    @Min(value = 1,message = "当前页不能小于1!!!")
    private int pageSize;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
