package com.study.demo.taotao_dao.mapper;

import com.study.demo.taotao_pojo.pojo.TbTypeTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TemplateMapper {

    /**
     * 插入模板类型
     * @param tbTypeTemplate 模板信息
     * @return 插入成功返回 1
     */
    int insertTemplate(TbTypeTemplate tbTypeTemplate);

    /**
     * 获取模板数据
     * @return 模板集合
     */
    List<TbTypeTemplate> selectTbTypeTemplates();

    /**
     * 更新模板表信息
     * @param tbTypeTemplate 模板信息
     * @return 修改成功返回 1
     */
    int updateTemplate(TbTypeTemplate tbTypeTemplate);

    /**
     * 根据ID删除模板信息
     * @param id 模板ID
     * @return 删除成功返回 大于1
     */
    int deleteTemplate(Integer id);


    /**
     * 获取模板绑定的规格信息
     * @param typeId 模板ID
     * @return Json 规格信息
     */
    String selectSpecIds(Integer typeId);
}
