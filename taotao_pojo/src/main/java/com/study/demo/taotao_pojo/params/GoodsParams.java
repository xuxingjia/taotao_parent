package com.study.demo.taotao_pojo.params;

import com.study.demo.taotao_pojo.pojo.TbGoods;
import com.study.demo.taotao_pojo.pojo.TbGoodsDesc;

import java.io.Serializable;

public class GoodsParams implements Serializable {

    private TbGoods goods;

    private TbGoodsDesc goodsDesc;

    public TbGoods getGoods() {
        return goods;
    }

    public void setGoods(TbGoods goods) {
        this.goods = goods;
    }

    public TbGoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(TbGoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }
}
