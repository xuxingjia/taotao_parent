package com.study.demo.taotao_service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.ErrorEnum;
import com.study.demo.taotao_dao.mapper.SpecificationMapper;
import com.study.demo.taotao_interface.service.SpecificationService;
import com.study.demo.taotao_pojo.params.BaseId;
import com.study.demo.taotao_pojo.params.BasePage;
import com.study.demo.taotao_pojo.params.SpecificationParams;
import com.study.demo.taotao_pojo.pojo.TbSpecification;
import com.study.demo.taotao_pojo.pojo.TbSpecificationOption;
import com.study.demo.taotao_pojo.result.SpecificationResult;
import com.study.demo.taotao_pojo.utils.ValidatorImpl;
import com.study.demo.taotao_pojo.utils.ValidatorResult;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private ValidatorImpl validator;
    @Autowired
    private SpecificationMapper mapper;

    @Override
    public List<SpecificationResult> selectSpecifications(BasePage basePage) throws BusinessException {
        ValidatorResult result = this.validator.validator(basePage);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        PageHelper.startPage(basePage.getPage(), basePage.getPageSize());
        List<SpecificationResult> specifications = mapper.selectSpecifications();
        PageInfo<SpecificationResult> pageInfo = new PageInfo<>(specifications);
        return pageInfo.getList();
    }

    @Override
    public boolean insertSpecification(SpecificationParams specificationParam) throws BusinessException {
        ValidatorResult result = this.validator.validator(specificationParam);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        TbSpecification specification = convertSpecificationFromSpecificationParam(specificationParam);
        int insertStatus = mapper.insertSpecification(specification);
        if (insertStatus == 1) {
            List<TbSpecificationOption> tbSpecificationOptions = convertSpecificationOptionFromSpecificationParam(specificationParam, specification.getId());
            int insertOptionStatus = -1;
            for (TbSpecificationOption option : tbSpecificationOptions) {
                ValidatorResult optionResult = this.validator.validator(option);
                if (optionResult.isError()) {
                    throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, optionResult.getErrorMassage());
                }
                insertOptionStatus = mapper.insertSpecificationOption(option);
            }

            return insertOptionStatus == 1;
        }
        return false;
    }

    @Override
    public boolean updateSpecification(SpecificationParams specificationParam) throws BusinessException {
        ValidatorResult result = this.validator.validator(specificationParam);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        TbSpecification specification = convertSpecificationFromSpecificationParam(specificationParam);
        int insertStatus = mapper.updateSpecification(specification);
        int insertOptionStatus = -1;

        int deleteStatus = mapper.deleteSepcificationOption(specification.getId());
        if (deleteStatus>0){
            for (TbSpecificationOption option : specificationParam.getSpecificationOptions()) {
                ValidatorResult optionResult = this.validator.validator(option);
                if (optionResult.isError()) {
                    throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, optionResult.getErrorMassage());
                }
                insertOptionStatus = mapper.insertSpecificationOption(option);
            }
        }
        return insertOptionStatus == 1||insertStatus==1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSpecification(BaseId baseId) throws BusinessException {
        if (baseId.getId()==null ||baseId.getId()<=0){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,"规格ID错误!!!");
        }
        SpecificationResult specification = mapper.selectSpecification(baseId.getId());
        if (specification==null){
            throw new BusinessException(ErrorEnum.RESULT_ISEMPTY,"规格不存在!!!");
        }
        int deleteOptionStatus = mapper.deleteSepcificationOption(baseId.getId());
        int deleteStatus = mapper.deleteSpecification(baseId.getId());
        return deleteStatus==1&&deleteOptionStatus>0;
    }

    @Override
    public SpecificationResult selectSpecification(BaseId baseId) throws BusinessException {
        if (baseId.getId()==null ||baseId.getId()<=0){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,"规格ID错误!!!");
        }
        return mapper.selectSpecification(baseId.getId());
    }

    /**
     * 获取规格选项表信息
     * @param specificationParam 规格信息
     * @return 规格表集合信息
     */
    private List<TbSpecificationOption> convertSpecificationOptionFromSpecificationParam(SpecificationParams specificationParam, @NotNull Integer id) {
        List<TbSpecificationOption> specificationOptions = specificationParam.getSpecificationOptions();
        for (TbSpecificationOption option :
                specificationOptions) {
            option.setSpecId(id);
        }
        return specificationOptions;
    }

    /**
     * 获取规格表格信息
     *
     * @param specificationParam 规格信息
     * @return 规格表格信息
     */
    private TbSpecification convertSpecificationFromSpecificationParam(@NotBlank SpecificationParams specificationParam) {
        TbSpecification tbSpecification = new TbSpecification();
        BeanUtils.copyProperties(specificationParam, tbSpecification);
        return tbSpecification;
    }
}
