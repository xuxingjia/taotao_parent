package com.study.demo.taotao_pojo.params;

import java.io.Serializable;

public class GoodsSpecValue implements Serializable {

    private String specName;
    private String specValue;

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }
}
