package com.study.demo.taotao_dao.mapper;

import com.study.demo.taotao_pojo.pojo.TbSpecification;
import com.study.demo.taotao_pojo.pojo.TbSpecificationOption;
import com.study.demo.taotao_pojo.result.SpecificationResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpecificationMapper {

    /**
     * 获取规格信息
     * @return 规格集合
     */
    List<SpecificationResult> selectSpecifications();

    /**
     * 插入规格信息
     * @param specification 规格信息
     * @return 插入成功返回 1
     */
    int insertSpecification(TbSpecification specification);

    /**
     * 插入规格选项
     * @param specificationOption 规格选项信息
     * @return 插入成功返回 1
     */
    int insertSpecificationOption(TbSpecificationOption specificationOption);

    /**
     * 更新规格信息
     * @param specification 规格信息
     * @return 更新成功返回 1
     */
    int updateSpecification(TbSpecification specification);

    /**
     * 更新规格选项信息
     * @param specificationOption 规格选项信息
     * @return 更新成功返回 1
     */
    int updateSpecificationOption(TbSpecificationOption specificationOption);

    /**
     * 删除规格选项
     * @param id 规格ID
     * @return 删除成功返回 1
     */
    int deleteSepcificationOption(Integer id);

    /**
     * 删除规格
     * @param id 规格id
     * @return 删除成功返回 1
     */
    int deleteSpecification(Integer id);

    /**
     * 根据ID获取规格信息
     * @param id 规格ID
     * @return 规格信息
     */
    SpecificationResult selectSpecification(Integer id);

    /**
     * 获取规格信息根据id
     * @param id 规格ID
     * @return 规格扩展信息
     */
    List<TbSpecificationOption> selectSpecificationOptionBySpecId(Integer id);
}
