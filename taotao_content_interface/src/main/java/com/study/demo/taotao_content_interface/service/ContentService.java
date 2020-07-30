package com.study.demo.taotao_content_interface.service;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_pojo.params.BaseId;
import com.study.demo.taotao_pojo.params.ContentCategoryParams;
import com.study.demo.taotao_pojo.params.UpdateContentParams;
import com.study.demo.taotao_pojo.pojo.TbContent;
import com.study.demo.taotao_pojo.pojo.TbContentCategory;

import java.util.List;

public interface ContentService {

    /**
     * 获取广告列表类型信息
     * @return 广告类型
     */
    List<TbContentCategory> selectContentCategorys();

    /**
     * 插入广告信息
     * @param content 广告信息
     * @return 是否插入成功
     */
    boolean insertContent(TbContent content) throws BusinessException;

    /**
     * 删除广告信息
     * @param id 广告ID
     * @return 是否删除成功
     */
    boolean deleteContent(BaseId id) throws BusinessException;

    /**
     * 修改广告信息
     * @param content 广告信息
     * @return 是否修改成功
     */
    boolean updateContent(UpdateContentParams content) throws BusinessException;

    /**
     * 获取广告列表信息
     * @param params 广告分类ID与分类信息
     * @return 广告列表
     */
    List<TbContent> selectContentByCategoryId(ContentCategoryParams params) throws BusinessException;
}
