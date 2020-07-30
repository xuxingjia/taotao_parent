package com.study.demo.taotao_interface.service;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_pojo.params.BaseId;
import com.study.demo.taotao_pojo.params.ItemCatParams;
import com.study.demo.taotao_pojo.params.ItemParams;
import com.study.demo.taotao_pojo.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {

    /**
     * 查询分类列表
     * @param params 查询分类级别
     * @return 商品分类列表
     */
    List<TbItemCat> selectTbItemCatByParentId(ItemCatParams params) throws BusinessException;

    /**
     * 插入商品分类信息
     * @param tbItemCat 商品分类信息
     * @return 是否插入成功
     */
    boolean insertTbItemCat(TbItemCat tbItemCat) throws BusinessException;

    /**
     * 修改商品分类
     * @param itemCat 商品分类信息
     * @return 是否更新成功
     */
    boolean updateItemCat(TbItemCat itemCat) throws BusinessException;

    /**
     * 删除商品分类信息
     * @param baseId 商品分类信息ID
     * @return 是否删除成功
     */
    boolean deleteItemCat(BaseId baseId) throws BusinessException;

    /**
     * 修改商品审核状态
     * @param itemParams 商品IDs 审核状态
     * @return 是否修改成功
     */
    boolean updateTbItemStatus(ItemParams itemParams) throws BusinessException;
}
