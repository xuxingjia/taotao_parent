package com.study.demo.taotao_dao.mapper;

import com.study.demo.taotao_pojo.params.AlterSellerStatusParams;
import com.study.demo.taotao_pojo.pojo.TbSeller;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerMapper {
    /**
     * 插入商家入驻信息
     * @param seller 商家信息
     * @return 插入成功返回 1
     */
    int insertSeller(TbSeller seller);

    /**
     * 获取待审核商家列表
     * @param checkStatus 审核状态
     * @return 待审核商家
     */
    List<TbSeller> selectCheckPendingTbSellers(String checkStatus);

    /**
     * 根据ID 获取商家信息
     * @param sellerId 商家ID
     * @return 商家信息
     */
    TbSeller selectTbSellerBySellerId(String sellerId);

    /**
     * 审核商家信息
     * @param params 审核状态
     * @return 是否审核成功
     */
    int updateTbSellerStatusById(AlterSellerStatusParams params);

    /**
     * 获取商家名字
     * @param sellerId 商家ID
     * @return 商家名字
     */
    String selectSellerName(String sellerId);
}
