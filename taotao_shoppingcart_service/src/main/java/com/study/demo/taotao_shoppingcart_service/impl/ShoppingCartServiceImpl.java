package com.study.demo.taotao_shoppingcart_service.impl;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.ErrorEnum;
import com.study.demo.taotao_dao.mapper.ItemCatMapper;
import com.study.demo.taotao_pojo.pojo.TbItem;
import com.study.demo.taotao_pojo.pojo.TbOrderItem;
import com.study.demo.taotao_pojo.result.ShoppingCartResult;
import com.study.demo.taotao_shoppingcart_interface.service.ShoppingCartService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<ShoppingCartResult> addShoppingCart(List<ShoppingCartResult> shoppingCartResults, long itemId, Integer num) throws BusinessException {
        TbItem tbItem = itemCatMapper.selectTbItemById(itemId);
        if (tbItem == null) {
            throw new BusinessException(ErrorEnum.RESULT_ISEMPTY, "商品ID为无效ID!!!");
        }
        return getShoppingCart(shoppingCartResults, num, tbItem);
    }

    /**
     * 获取购物车列表
     * @param shoppingCartResults 原有的购物车列表
     * @param num 购买数量
     * @param tbItem 根据商品ID查询出来的购买商品
     * @return 添加商品后的购物车
     */
    private List<ShoppingCartResult> getShoppingCart(@NotNull List<ShoppingCartResult> shoppingCartResults, Integer num,@NotNull TbItem tbItem) {
        //判断购物车中是否存在该商家
        for (ShoppingCartResult cartResult : shoppingCartResults) {
            //购物车中商家id是否存在
            if (cartResult.getSellerId().equals(tbItem.getSellerId())) {
                //查看购物车下是否有
                for (TbOrderItem order : cartResult.getOrderItems()) {
                    if (order.getGoodsId() == tbItem.getGoodsId()) {
                        //如果商品存在购物车中   就修改商品数量
                        order.setNum(order.getNum() + num);
                        return shoppingCartResults;
                    } else {
                        //如果商品不存在购物车中  将商品添加到购物车中
                        List<TbOrderItem> orderItems = cartResult.getOrderItems();
                        TbOrderItem item = getTbOrderItem(num, tbItem);
                        orderItems.add(item);
                        return shoppingCartResults;
                    }
                }
            }
        }
        //购物车列表中没有该商家
        ShoppingCartResult result = new ShoppingCartResult();
        result.setSellerName(tbItem.getSeller());
        result.setSellerId(tbItem.getSellerId());
        ArrayList<TbOrderItem> list = new ArrayList<>();
        list.add(getTbOrderItem(num, tbItem));
        result.setOrderItems(list);
        shoppingCartResults.add(result);
        return  shoppingCartResults;
    }

    /**
     * 获取购物车商品
     *
     * @param num    购物数量
     * @param tbItem 根据商品ID查询到的商品
     * @return 购物车商品
     */
    private TbOrderItem getTbOrderItem(Integer num, @NotNull TbItem tbItem) {
        TbOrderItem item = new TbOrderItem();
        item.setNum(num);
        item.setPicPath(tbItem.getImage());
        item.setPrice(tbItem.getPrice());
        item.setTotalFee(BigDecimal.valueOf(tbItem.getPrice().doubleValue() * num));
        item.setGoodsId(tbItem.getGoodsId());
        item.setTitle(tbItem.getTitle());
        item.setSellerId(tbItem.getSellerId());
        return item;
    }
}
