package com.study.demo.taotao_dao.mapper;

import com.study.demo.taotao_pojo.dao.GoodsAtrributeDao;
import com.study.demo.taotao_pojo.dao.GoodsAttributeItemsDao;
import com.study.demo.taotao_pojo.dao.GoodsImageUrlDao;
import com.study.demo.taotao_pojo.dao.GoodsSpecificationDao;
import com.study.demo.taotao_pojo.params.BaseLongId;
import com.study.demo.taotao_pojo.params.GoodsEnableSpecParams;
import com.study.demo.taotao_pojo.params.GoodsInfoParams;
import com.study.demo.taotao_pojo.params.SelectGoodsParams;
import com.study.demo.taotao_pojo.pojo.TbGoods;
import com.study.demo.taotao_pojo.pojo.TbGoodsDesc;
import com.study.demo.taotao_pojo.pojo.TbGoodsImage;
import com.study.demo.taotao_pojo.pojo.TbItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {

    /**
     * 插入商品信息
     * @param goods 商品信息
     * @return 成功返回 1
     */
    int insertGoods(TbGoods goods);

    /**
     * 插入商品信息
     * @param goodsDesc 商品信息
     * @return 成功返回 1
     */
    int insertGoodsDesc(TbGoodsDesc goodsDesc);

    /**
     * 获取商品的扩展属性
     * @param id 商品模板ID
     * @return 商品扩展属性
     */
    String selectGoodsAttributeItems(Integer id);

    /**
     * 插入商品的扩展属性
     * @param dao 扩展属性数据库数据
     * @return 是否插入成功
     */
    int insertGoodsAttributeItems(GoodsAttributeItemsDao dao);


    /**
     * 获取商品级别属性
     * @param id 商品ID
     * @return 商品三级分类ID
     */
    GoodsSpecificationDao selectGoodsSpecificaton(Long id);

    /**
     * 获取商品sellerID brandID 分类ID 商品名称信息
     * @param id 商品ID
     * @return 商品属性信息
     */
    GoodsAtrributeDao selectGoodsAttribute(Long id);

    /**
     * 插入商品规格属性
     * @param item 规格属性信息
     * @return 插入成功返回 1
     */
    int insertGoodsSku(TbItem item);

    /**
     * 修改是否起用规格
     * @param goodsEnableSpecParams 商品Id
     * @return 修改成功返回 1
     */
    int updateGoodsIsEnableSpecById(GoodsEnableSpecParams goodsEnableSpecParams);

    /**
     * 获取商家名下的所有商品
     * @param goodsParams 商家查询信息
     * @return 商品列表
     */
    List<TbGoods> selectGoodsBySellerId(SelectGoodsParams goodsParams);

    /**
     * 删除商品
     * @param id  商品ID
     * @return 删除成功返回 1
     */
    int deleteGoods(Long id);

    /**
     *更新商品基本信息
     * @param goodsInfoParams 商品基本信息
     * @return 更新成功返回 1
     */
    int updateGoodsInfo(TbGoods goodsInfoParams);

    /**
     * 将商品图片绑定到指定商品上面
     * @param url 商品图片url
     * @return 添加成功后返回 1
     */
    int insertGoodsIamgeUrl(TbGoodsImage url);

    /**
     * 根据id获取图片信息
     * @param id 图片id
     * @return 图片信息
     */
    TbGoodsImage selectGoodsImage(Integer id);

    /**
     * 删除商品图片信息
     * @param id 商品图片ID
     * @return 删除成功返回 1
     */
    int deleteGoodsImageById(Integer id);

    /**
     * 获取商品图片列表
     * @param id 商品id
     * @return 商品图片列表
     */
    List<TbGoodsImage> selectGoodsImageByGoodsId(Long id);
}
