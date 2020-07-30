package com.study.demo.taotao_seller_service.impl;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.ErrorEnum;
import com.study.demo.taotao_dao.mapper.SellerMapper;
import com.study.demo.taotao_interface.service.SellerService;
import com.study.demo.taotao_pojo.params.AlterSellerStatusParams;
import com.study.demo.taotao_pojo.params.BaseId;
import com.study.demo.taotao_pojo.params.BaseStrId;
import com.study.demo.taotao_pojo.params.CheckPendingSellerParams;
import com.study.demo.taotao_pojo.pojo.TbSeller;
import com.study.demo.taotao_pojo.utils.ValidatorImpl;
import com.study.demo.taotao_pojo.utils.ValidatorResult;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private SellerMapper mapper;

    @Override
    public boolean insertSeller(TbSeller seller) throws BusinessException {
        ValidatorResult result = this.validator.validator(seller);
        if (result.isError()){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,result.getErrorMassage());
        }
        TbSeller initSeller = convertTbSellerFromParams(seller);
        try {
            int insertStatus = mapper.insertSeller(initSeller);
            return insertStatus==1;
        }catch (DuplicateKeyException e){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,"注入商店ID重复!");
        }
    }

    @Override
    public List<TbSeller> selectCheckPendingTbSellers(CheckPendingSellerParams params) throws BusinessException {
        ValidatorResult result = this.validator.validator(params);
        if (result.isError()){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,result.getErrorMassage());
        }
        List<TbSeller> sellers = mapper.selectCheckPendingTbSellers(params.getCheckStatus());
        if (sellers==null){
            throw new BusinessException(ErrorEnum.RESULT_ISEMPTY,"没有待审核数据!!!");
        }else {
            return sellers;
        }
    }

    @Override
    public TbSeller selectTbSellerBySellerId(BaseStrId id) throws BusinessException {
        if (id.getId()==null){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,"商家ID不能为空!!!");
        }
        TbSeller seller = mapper.selectTbSellerBySellerId(id.getId());
        if (seller==null){
            throw new BusinessException(ErrorEnum.RESULT_ISEMPTY,"没有查找到商家ID!!!");
        }else {
            return seller;
        }
    }

    @Override
    public boolean alterTbSellerStatus(AlterSellerStatusParams params) throws BusinessException {
        ValidatorResult result = this.validator.validator(params);
        if (result.isError()||params.getId()==null){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,result.getErrorMassage()+",商家ID不能为空!!!");
        }
        int updateStatus = mapper.updateTbSellerStatusById(params);
        return updateStatus==1;
    }

    /**
     * 添加初始化属性
     * @param seller 入驻商家信息
     * @return 入驻商家信息初始化
     */
    private TbSeller convertTbSellerFromParams(TbSeller seller) {
        seller.setStatus("1");
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateTime = dateFormat.format(date);
        seller.setCreateTime(Timestamp.valueOf(dateTime));
        return seller;
    }
}
