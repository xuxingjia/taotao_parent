package com.study.demo.taotao_service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.ErrorEnum;
import com.study.demo.taotao_dao.mapper.BrandMapper;
import com.study.demo.taotao_interface.service.BrandService;
import com.study.demo.taotao_pojo.params.BasePage;
import com.study.demo.taotao_pojo.pojo.TbBrand;
import com.study.demo.taotao_pojo.utils.ValidatorImpl;
import com.study.demo.taotao_pojo.utils.ValidatorResult;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author cc
 */
@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandMapper mapper;
    @Autowired
    private ValidatorImpl validator;

    @Override
    public List<TbBrand> getAllBrand(BasePage basePage) throws BusinessException {
        ValidatorResult result = validator.validator(basePage);
        if (result.isError()){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,result.getErrorMassage());
        }
        PageHelper.startPage(basePage.getPage(),basePage.getPageSize());
        List<TbBrand> allBrand = mapper.getAllBrand();
        if (allBrand==null){
            throw new BusinessException(ErrorEnum.RESULT_ISEMPTY,"数据为空!!!");
        }
        PageInfo<TbBrand> pageInfo = new PageInfo<>(allBrand);
        return pageInfo.getList();
    }

    @Override
    public TbBrand getBrandById(Integer id) throws BusinessException {
        if (id!=null&&id>0){
            TbBrand brandInfo = mapper.getBrandById(id);
            if (brandInfo==null){
                throw new BusinessException(ErrorEnum.RESULT_ISEMPTY,"数据为空!!!");
            }else {
                return brandInfo;
            }
        }else {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,"id 参数异常!");
        }
    }

    @Override
    public boolean updateBrandById(TbBrand brand) throws BusinessException {
        ValidatorResult result = this.validator.validator(brand);
        if (result.isError()){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,result.getErrorMassage());
        }
        int updateResult = mapper.updateBrandById(brand);
        return updateResult==1;
    }

    @Override
    public boolean deleteBrandById(Integer id) throws BusinessException {
        if (id!=null&&id>0){
            int deleteResult = mapper.deleteBrandById(id);
            return deleteResult==1;
        }else {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,"id 参数异常!");
        }
    }

    @Override
    public TbBrand insertBrand(TbBrand brand) throws BusinessException {
        if (brand.getName()!=null&&brand.getFirstChar()!=null){
            Integer insertResult = mapper.insertBrand(brand);
            if (insertResult==1){
                return mapper.getBrandById(brand.getId());
            }else {
                return null;
            }
        }else {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,"名称或者首字母不能为空!");
        }
    }
}