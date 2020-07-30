package com.study.demo.taotao_pojo.params;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsInfoParams extends BaseLongId implements Serializable {

    //一级类目
    @NotNull(message = "一级分类ID不能为空")
    private Integer category1Id;
    //二级类目
    @NotNull(message = "二级分类ID不能为空")
    private Integer category2Id;
    //三级类目
    @NotNull(message = "三级分类ID不能为空")
    private Integer category3Id;
    @NotBlank(message = "商品名称不能为空!!!")
    private String goodsName;
    @NotNull(message = "品牌ID不能为空!!!")
    private Integer brandId;
    //副标题
    @NotBlank(message = "副标题不能为空!!!")
    private String caption;
    //商城价
//    @NotNull(message = "价格不能为空!!!")
    private BigDecimal price;
    //分类模板ID
    @NotNull(message = "分类模板ID不能为空")
    private Integer typeTemplateId;
    //描述
    @NotBlank(message = "描述不能为空")
    private String introduction;
    //包装列表
    @NotBlank(message = "包装列表不能为空")
    private String packageList;
    //售后服务
    @NotBlank(message = "售后服务不能为空")
    private String saleService;


    public Integer getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(Integer category1Id) {
        this.category1Id = category1Id;
    }

    public Integer getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(Integer category2Id) {
        this.category2Id = category2Id;
    }

    public Integer getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(Integer category3Id) {
        this.category3Id = category3Id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getTypeTemplateId() {
        return typeTemplateId;
    }

    public void setTypeTemplateId(Integer typeTemplateId) {
        this.typeTemplateId = typeTemplateId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPackageList() {
        return packageList;
    }

    public void setPackageList(String packageList) {
        this.packageList = packageList;
    }

    public String getSaleService() {
        return saleService;
    }

    public void setSaleService(String saleService) {
        this.saleService = saleService;
    }
}
