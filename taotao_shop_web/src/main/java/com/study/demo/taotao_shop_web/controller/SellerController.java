package com.study.demo.taotao_shop_web.controller;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.CommonReturnType;
import com.study.demo.taotao_interface.service.SellerService;
import com.study.demo.taotao_pojo.params.AlterSellerStatusParams;
import com.study.demo.taotao_pojo.params.BaseStrId;
import com.study.demo.taotao_pojo.params.CheckPendingSellerParams;
import com.study.demo.taotao_pojo.pojo.TbSeller;
import com.study.demo.taotao_shop_web.common.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController extends BaseController {

    @Reference
    private SellerService service;

    @RequestMapping(value = "/insertSeller",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType insertSeller(@RequestBody TbSeller seller) throws BusinessException {
        boolean insertStatus = service.insertSeller(seller);
        if (insertStatus){
            return CommonReturnType.create("","添加成功!!!");
        }else{
            return CommonReturnType.create("","添加失败!!!");
        }
    }

    @RequestMapping(value = "/selectCheckPendingTbSellers",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType selectCheckPendingTbSellers(@RequestBody CheckPendingSellerParams params) throws BusinessException {
        List<TbSeller> sellers = service.selectCheckPendingTbSellers(params);
        return CommonReturnType.create(sellers);
    }

    @RequestMapping(value = "/selectTbSellerBySellerId",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType selectTbSellerBySellerId(@RequestBody BaseStrId id) throws BusinessException {
        TbSeller seller = service.selectTbSellerBySellerId(id);
        return CommonReturnType.create(seller);
    }

    @RequestMapping(value = "/alterTbSellerStatus",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType alterTbSellerStatus(@RequestBody AlterSellerStatusParams params) throws BusinessException {
        boolean alterStatus = service.alterTbSellerStatus(params);
        if (alterStatus){
            return CommonReturnType.create("","审核成功!!!");
        }else {
            return CommonReturnType.create("","审核失败!!!");
        }
    }
}
