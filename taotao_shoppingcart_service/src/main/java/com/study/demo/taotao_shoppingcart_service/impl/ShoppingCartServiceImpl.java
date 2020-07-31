package com.study.demo.taotao_shoppingcart_service.impl;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.ErrorEnum;
import com.study.demo.taotao_dao.mapper.ItemCatMapper;
import com.study.demo.taotao_pojo.pojo.TbItem;
import com.study.demo.taotao_pojo.result.ShoppongCartResult;
import com.study.demo.taotao_shoppingcart_interface.service.ShoppingCartService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ItemCatMapper itemCatMapper;
    @Override
    public List<ShoppongCartResult> addShoppingCart(List<ShoppongCartResult> shoppongCartResults, long itemId, Integer num) throws BusinessException {
        TbItem tbItem = itemCatMapper.selectTbItemById(itemId);
        if (tbItem==null){
            throw new BusinessException(ErrorEnum.RESULT_ISEMPTY,"商品ID为无效ID!!!");
        }
        //判断购物车中是否存在该商家

        return null;
    }
}
