package com.study.demo.taotao_pojo.result;

import com.study.demo.taotao_pojo.pojo.TbSpecification;
import com.study.demo.taotao_pojo.pojo.TbSpecificationOption;

import java.io.Serializable;
import java.util.List;

public class GoodsSpecificationResult implements Serializable {

    private TbSpecification specification;

    private List<TbSpecificationOption> specificationOptions;

    public TbSpecification getSpecification() {
        return specification;
    }

    public void setSpecification(TbSpecification specification) {
        this.specification = specification;
    }

    public List<TbSpecificationOption> getSpecificationOptions() {
        return specificationOptions;
    }

    public void setSpecificationOptions(List<TbSpecificationOption> specificationOptions) {
        this.specificationOptions = specificationOptions;
    }
}
