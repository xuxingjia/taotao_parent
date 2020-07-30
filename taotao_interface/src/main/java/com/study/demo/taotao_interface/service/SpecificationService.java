package com.study.demo.taotao_interface.service;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_pojo.params.BaseId;
import com.study.demo.taotao_pojo.params.BasePage;
import com.study.demo.taotao_pojo.params.SpecificationParams;
import com.study.demo.taotao_pojo.result.SpecificationResult;

import java.util.List;

public interface SpecificationService {

    /**
     * 获取规格信息
     * @param basePage 分页数据
     * @return 规格集合
     */
    List<SpecificationResult> selectSpecifications(BasePage basePage) throws BusinessException;

    /**
     * 插入规格信息
     * @param specificationParam 规格信息
     * @return 插入是否成功
     */
    boolean insertSpecification(SpecificationParams specificationParam) throws BusinessException;

    /**
     * 更新规格信息
     * @param specificationParam 规格信息
     * @return 更新规格信息是否成功
     */
    boolean updateSpecification(SpecificationParams specificationParam) throws BusinessException;

    /**
     * 删除规格信息
     * @param baseId 规格信息ID
     * @return 是否删除成功
     * @throws BusinessException 异常信息
     */
    boolean deleteSpecification(BaseId baseId) throws BusinessException;

    /**
     * 根据ID 获取规格信息
     * @param baseId 规格ID
     * @return 规格信息
     */
    SpecificationResult selectSpecification(BaseId baseId) throws BusinessException;
}
