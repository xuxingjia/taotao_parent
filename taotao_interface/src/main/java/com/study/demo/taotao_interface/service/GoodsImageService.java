package com.study.demo.taotao_interface.service;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_pojo.params.BaseLongId;
import com.study.demo.taotao_pojo.pojo.TbGoodsImage;

import java.util.List;

public interface GoodsImageService {
    /**
     * 将商品图片绑定到指定商品上面
     * @param url 商品图片url
     * @return 是否添加成功
     */
    boolean insertGoodsIamgeUrl(TbGoodsImage url) throws BusinessException;


    /**
     * 获取商品图片列表
     * @param id 商品id
     * @return 商品图片列表
     */
    List<TbGoodsImage> selectGoodsImageByGoodsId(BaseLongId id) throws BusinessException;
}
