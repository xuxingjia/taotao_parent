package com.study.demo.taotao_dao.mapper;

import com.study.demo.taotao_pojo.params.ContentCategoryParams;
import com.study.demo.taotao_pojo.params.UpdateContentParams;
import com.study.demo.taotao_pojo.pojo.TbContent;
import com.study.demo.taotao_pojo.pojo.TbContentCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {

    /**
     * 获取广告列表类型信息
     * @return 广告类型
     */
    List<TbContentCategory> selectContentCategorys();

    /**
     * 插入广告信息
     * @param content 广告信息
     * @return 插入成功后返回 1
     */
    int insertContent(TbContent content);

    /**
     * 删除广告信息
     * @param id 广告ID
     * @return 删除成功返回 1
     */
    int deleteContent(Integer id);

    /**
     * 修改广告信息
     * @param content 广告信息
     * @return 修改成功返回 1
     */
    int updateContent(UpdateContentParams content);

    /**
     * 获取广告列表信息
     * @param id 查询信息与分页信息
     * @return 广告列表
     */
    List<TbContent> selectContentByCategoryId(Integer id);

    /**
     * 获取广告信息根据广告id
     * @param id 广告id
     * @return 广告信息
     */
    TbContent selectContentById(Integer id);
}
