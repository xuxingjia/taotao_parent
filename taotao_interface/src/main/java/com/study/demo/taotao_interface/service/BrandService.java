package com.study.demo.taotao_interface.service;


import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_pojo.params.BasePage;
import com.study.demo.taotao_pojo.pojo.TbBrand;

import java.util.List;

public interface BrandService {

    /**
     * 获取品牌集合
     * @return 品牌集合
     */
    List<TbBrand> getAllBrand(BasePage basePage) throws BusinessException;

    /**
     *
     * @param id 品牌ID
     * @return 品牌信息
     * @throws BusinessException 异常
     */
    TbBrand getBrandById(Integer id) throws BusinessException;

    /**
     *更新品牌根据id
     * @param brand 品牌信息
     * @return 是否成功
     */
    boolean updateBrandById(TbBrand brand) throws BusinessException;

    /**
     *删除品牌根据id
     * @param id 品牌ID
     * @return 删除是否成功
     */
    boolean deleteBrandById(Integer id) throws BusinessException;

    /**
     * 插入品牌信息
     * @param brand 品牌信息
     * @return 返回插入信息
     */
    TbBrand insertBrand(TbBrand brand) throws BusinessException;
}
