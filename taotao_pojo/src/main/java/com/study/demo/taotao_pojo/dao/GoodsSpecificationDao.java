package com.study.demo.taotao_pojo.dao;

public class GoodsSpecificationDao {
    //一级类目
    private Integer category1Id;
    //二级类目
    private Integer category2Id;
    //三级类目
    private Integer category3Id;

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
}
