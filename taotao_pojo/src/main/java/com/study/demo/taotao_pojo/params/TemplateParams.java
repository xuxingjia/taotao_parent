package com.study.demo.taotao_pojo.params;

import com.study.demo.taotao_pojo.pojo.CustomAttrbuteItems;
import com.study.demo.taotao_pojo.pojo.TbBrand;
import com.study.demo.taotao_pojo.pojo.TbSpecification;
import com.study.demo.taotao_pojo.result.BrandResult;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

public class TemplateParams implements Serializable {

    private Integer id;
    @NotBlank(message = "模板名称不能为空")
    private String name;

    private List<BrandResult> brands;

    private List<TbSpecification> specifications;

    private List<CustomAttrbuteItems> attrbuteItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BrandResult> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandResult> brands) {
        this.brands = brands;
    }

    public List<TbSpecification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<TbSpecification> specifications) {
        this.specifications = specifications;
    }

    public List<CustomAttrbuteItems> getAttrbuteItems() {
        return attrbuteItems;
    }

    public void setAttrbuteItems(List<CustomAttrbuteItems> attrbuteItems) {
        this.attrbuteItems = attrbuteItems;
    }
}
