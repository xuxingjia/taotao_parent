package com.study.demo.taotao_interface.service;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_pojo.params.*;
import com.study.demo.taotao_pojo.pojo.TbGoods;
import com.study.demo.taotao_pojo.pojo.TbGoodsImage;
import com.study.demo.taotao_pojo.result.GoodsAttributeItemsResult;
import com.study.demo.taotao_pojo.result.GoodsResult;
import com.study.demo.taotao_pojo.result.GoodsSpecificationResult;

import java.util.List;

public interface GoodsService {

    /**
     * 插入商品基本信息
     * @param goodsInfoParams 商品基本信息
     * @return 商品基本信息ID
     */
    BaseStrId insertGoodsInfo(GoodsInfoParams goodsInfoParams) throws BusinessException;

    /**
     * 获取商品的扩展属性
     * @param id 商品模板ID
     * @return 商品扩展属性
     */
    List<GoodsAttributeItemsResult> selectGoodsAttributeItems(BaseId id) throws BusinessException;

    /**
     * 插入商品的扩展属性
     * @param params 扩展属性数据
     * @return 是否插入成功
     */
    boolean insertGoodsAttributeItems(GoodsAttributeItemsParams params) throws BusinessException;

    /**
     * 删除图片
     * @param id 商品图片id
     * @return 是否删除成功
     */
    boolean deleteGoodsImage(BaseId id) throws BusinessException;

    /**
     * 获取商品规格信息
     * @param id 商品ID
     * @return 商品规格信息
     */
    List<GoodsSpecificationResult> selectGoodsSpecification(Long id) throws BusinessException;

    /**
     * 添加商品规格信息
     * @param goodsItemParams 商品规格信息
     * @return 是否添加成功
     */
    boolean insertGoodsSku(GoodsItemParams goodsItemParams) throws BusinessException;

    /**
     * 存入商品是否启用规格
     * @param enableSpecParams 规格数据
     * @return 是否存储成功
     */
    boolean isEnableSpec(GoodsEnableSpecParams enableSpecParams) throws BusinessException;

    /**
     * 获取所有商品信息
     * @param goodsParams 分页数据 查询数据
     * @return 商品信息
     */
    List<GoodsResult> selectGoods(SelectGoodsParams goodsParams) throws BusinessException;

    /**
     * 删除商品
     * @param longId 商品ID
     * @return 是否删除成功
     */
    boolean deleteGoods(BaseLongId longId) throws BusinessException;

    /**
     * 更新商品基本信息
     * @param goodsInfoParams 商品基本参数
     * @return 是否更新成功
     */
    boolean updateGodsInfo(GoodsInfoParams goodsInfoParams) throws BusinessException;

    /**
     * 获取商品图片列表
     * @param longId 商品ID
     * @return 商品列表
     */
    List<TbGoodsImage> selectGoodsImage(BaseLongId longId) throws BusinessException;

    void insertSolrItem();
}
