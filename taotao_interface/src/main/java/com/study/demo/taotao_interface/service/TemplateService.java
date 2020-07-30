package com.study.demo.taotao_interface.service;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_pojo.params.BaseId;
import com.study.demo.taotao_pojo.params.BasePage;
import com.study.demo.taotao_pojo.params.TemplateParams;

import java.util.List;

public interface TemplateService {

    /**
     * 插入模板信息
     * @param templateParams 模板信息
     * @return 是否插入成功
     */
    boolean insertTemplate(TemplateParams templateParams) throws BusinessException;

    /**
     * 查询模板数据
     * @param basePage 页面分页数据
     * @return 模板列表数据
     * @throws BusinessException 异常
     */
    List<TemplateParams> selectTemplates(BasePage basePage) throws BusinessException;

    /**
     * 修改模板信息
     * @param templateParams 模板信息
     * @return 是否更新成功
     */
    boolean updateTemplate(TemplateParams templateParams) throws BusinessException;

    /**
     * 根据id删除模板信息
     * @param id 模板id
     * @return 删除是否成功
     */
    boolean deleteTemplate(BaseId id) throws BusinessException;
}
