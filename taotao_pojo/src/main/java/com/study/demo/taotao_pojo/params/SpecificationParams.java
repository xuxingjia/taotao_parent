package com.study.demo.taotao_pojo.params;

import com.study.demo.taotao_pojo.pojo.TbSpecificationOption;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class SpecificationParams implements Serializable {
    @NotBlank(message = "规格名称不能为空!!!")
    private String specName;

    @NotNull(message = "规格ID不能为空!!")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private List<TbSpecificationOption> specificationOptions;

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
