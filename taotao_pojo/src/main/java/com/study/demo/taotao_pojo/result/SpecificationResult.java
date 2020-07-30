package com.study.demo.taotao_pojo.result;

import com.study.demo.taotao_pojo.pojo.TbSpecificationOption;

import java.io.Serializable;
import java.util.List;

public class SpecificationResult implements Serializable {

    private Integer id;
    private String specName;

    private List<TbSpecificationOption> specificationOptions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public List<TbSpecificationOption> getSpecificationOptions() {
        return specificationOptions;
    }

    public void setSpecificationOptions(List<TbSpecificationOption> specificationOptions) {
        this.specificationOptions = specificationOptions;
    }
}
