package com.study.demo.taotao_interface.service;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_pojo.params.AlterSellerStatusParams;
import com.study.demo.taotao_pojo.params.BaseStrId;
import com.study.demo.taotao_pojo.params.CheckPendingSellerParams;
import com.study.demo.taotao_pojo.pojo.TbSeller;

import java.util.List;

public interface SellerService {

    /**
     * 添加商家入驻
     * @param seller 商家信息
     * @return 是否添加成功
     */
    boolean insertSeller(TbSeller seller) throws BusinessException;

    /**
     * 获取待审核商家列表
     * @param params 分页数据 审核状态
     * @return 待审核商家
     */
    List<TbSeller> selectCheckPendingTbSellers(CheckPendingSellerParams params) throws BusinessException;

    /**
     * 根据sellerId 获取商家信息
     * @param id 商家id
     * @return 商家信息
     */
    TbSeller selectTbSellerBySellerId(BaseStrId id) throws BusinessException;

    /**
     * 审核商家
     * @param params 审核状态
     * @return 是否审核成功
     */
    boolean alterTbSellerStatus(AlterSellerStatusParams params) throws BusinessException;
}
