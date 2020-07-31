package com.study.demo.taotao_shoppingcart_interface.service;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_pojo.result.ShoppongCartResult;

import java.util.List;

public interface ShoppingCartService {

    /**
     * 添加购物车
     * @param shoppongCartResults 原有购物车信息
     * @param itemId 添加购物车商品ID
     * @param num 添加数量
     * @return 返回添加完成后的购物车信息
     */
    List<ShoppongCartResult> addShoppingCart(List<ShoppongCartResult> shoppongCartResults,long itemId,Integer num) throws BusinessException;
}
