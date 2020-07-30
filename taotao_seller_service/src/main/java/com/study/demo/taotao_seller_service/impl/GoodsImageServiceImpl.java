package com.study.demo.taotao_seller_service.impl;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.ErrorEnum;
import com.study.demo.taotao_dao.mapper.GoodsMapper;
import com.study.demo.taotao_interface.service.GoodsImageService;
import com.study.demo.taotao_pojo.params.BaseLongId;
import com.study.demo.taotao_pojo.pojo.TbGoodsImage;
import com.study.demo.taotao_pojo.utils.ValidatorImpl;
import com.study.demo.taotao_pojo.utils.ValidatorResult;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class GoodsImageServiceImpl implements GoodsImageService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private GoodsMapper mapper;

    @Override
    public boolean insertGoodsIamgeUrl(TbGoodsImage url) throws BusinessException {
        ValidatorResult result = validator.validator(url);
        if (result.isError()){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,result.getErrorMassage());
        }
        int insertSatus = mapper.insertGoodsIamgeUrl(url);
        return insertSatus==1;
    }

    @Override
    public List<TbGoodsImage> selectGoodsImageByGoodsId(BaseLongId id) throws BusinessException {
        if (id.getId()==null||id.getId()<0){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,"商品ID错误!!!");
        }
        List<TbGoodsImage> goodsImages = mapper.selectGoodsImageByGoodsId(id.getId());
        return goodsImages;
    }
}