package com.study.demo.taotao_dao.mapper;

import com.study.demo.taotao_pojo.pojo.TbBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    /**
     * 获取所有的品牌名
     * @return 品牌名集合
     */
    List<TbBrand> getAllBrand();

    /**
     * 根据id获取品牌信息
     * @param id 品牌id
     * @return 品牌信息
     */
    TbBrand getBrandById(Integer id);

    /**
     * 更新品牌根据id
     * @param brand 品牌信息
     * @return 更新成功返回1
     */
    int updateBrandById(TbBrand brand);

    /**
     *删除品牌根据id
     * @param id 品牌ID
     * @return 删除成功返回1
     */
    int deleteBrandById(Integer id);

    /**
     * 插入品牌信息
     * @param brand 品牌信息
     * @return 返回插入信息
     */
    int insertBrand(TbBrand brand);

    /**
     * 根据品牌ID查询品牌名称
     * @param brandId 品牌id
     * @return  品牌名称
     */
    String selectBrandNameById(Integer brandId);
}