package com.study.demo.taotao_dao.mapper;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_pojo.params.BaseId;
import com.study.demo.taotao_pojo.params.ItemCatParams;
import com.study.demo.taotao_pojo.pojo.TbItem;
import com.study.demo.taotao_pojo.pojo.TbItemCat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemCatMapper {

    /**
     * 查询分类列表
     * @param parentId 查询分类级别
     * @return 商品分类列表
     */
    List<TbItemCat> selectTbItemCatByParentId(Integer parentId);

    /**
     * 插入商品分类信息
     * @param tbItemCat 商品分类信息
     * @return 是否插入成功
     */
    int insertTbItemCat(TbItemCat tbItemCat);

    /**
     * 修改商品分类
     * @param itemCat 商品分类信息
     * @return 修改成功返回 1
     */
    int updateItemCat(TbItemCat itemCat);

    /**
     * 删除商品分类信息
     * @param id 商品分类信息ID
     * @return 删除成功返回大于1
     */
    int deleteItemCat(Integer id);

    /**
     * 获取模板ID
     * @param category3Id 规格ID
     * @return 模板ID
     */
    Integer selectTypeId(Integer category3Id);

    /**
     * 获取分级名称
     * @param categoryId 分级ID
     * @return 分级名称
     */
    String selectCategoryById(Integer categoryId);

    List<TbItem> insertSolrAllItem();

    /**
     * 查询所有item_cat数据
     * @return itemcat 集合
     */
    List<TbItemCat> selectAllItemCat();

    /**
     * 更新商品审核状态
     * @param ids 商品ids
     * @param status 审核状态
     * @return 修改成功返回 >1
     */
    int updateItemCatStatus(Integer[] ids, String status);

    /**
     * 根据商品ID 获取商品信息
     * @param itemId 商品ID
     * @return 商品信息
     */
    TbItem selectTbItemById(long itemId);
}