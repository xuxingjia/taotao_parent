package com.study.demo.taotao_shop_web.controller;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.CommonReturnType;
import com.study.demo.taotao_interface.service.GoodsService;
import com.study.demo.taotao_pojo.params.*;
import com.study.demo.taotao_pojo.pojo.TbGoodsImage;
import com.study.demo.taotao_pojo.result.GoodsAttributeItemsResult;
import com.study.demo.taotao_pojo.result.GoodsResult;
import com.study.demo.taotao_pojo.result.GoodsSpecificationResult;
import com.study.demo.taotao_shop_web.common.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goodsController")
public class GoodsController extends BaseController {
    @Reference
    private GoodsService service;

    @RequestMapping(value = "/insertGoodsInfo",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType insertGoodsInfo(@RequestBody GoodsInfoParams params) throws BusinessException {
        BaseStrId baseId = service.insertGoodsInfo(params);
        if (StringUtils.isEmpty(baseId.getId())){
            return CommonReturnType.create("","插入失败!!!");
        }else {
            return CommonReturnType.create(baseId,"插入成功!!!");
        }
    }

    @RequestMapping(value = "/selectGoodsAttributeItems",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType selectGoodsAttributeItems(@RequestBody BaseId id) throws BusinessException {
        List<GoodsAttributeItemsResult> goodsAttributeItemsResults = service.selectGoodsAttributeItems(id);
        if (goodsAttributeItemsResults.size()==0){
            return CommonReturnType.create("","该商品没有扩展属性!!!");
        }else {
            return CommonReturnType.create(goodsAttributeItemsResults);
        }
    }

    @RequestMapping(value = "/insertGoodsAttributeItems",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType insertGoodsAttributeItems(@RequestBody GoodsAttributeItemsParams params) throws BusinessException {
        boolean insertStatus = service.insertGoodsAttributeItems(params);
        if (insertStatus){
            return CommonReturnType.create("","插入成功!!!");
        }else {
            return CommonReturnType.create("","插入失败!!!");
        }
    }

    @RequestMapping(value = "/deleteGoodsImage",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType deleteGoodsImage(@RequestBody BaseId id) throws BusinessException {
        boolean deleteStatus = service.deleteGoodsImage(id);
        if (deleteStatus){
            return CommonReturnType.create("","删除成功!!!");
        }else {
            return CommonReturnType.create("","删除失败!!!");
        }
    }

    @RequestMapping(value = "/selectGoodsSpecifiction",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType selectGoodsSpecifiction(@RequestBody BaseLongId id) throws BusinessException {
        List<GoodsSpecificationResult> goodsSpecificationResults = service.selectGoodsSpecification(id.getId());
        return CommonReturnType.create(goodsSpecificationResults);
    }

    @RequestMapping(value = "/insertGoodsSku",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType insertGoodsSku(@RequestBody GoodsItemParams goodsItemParams) throws BusinessException {
        boolean insertStatus = service.insertGoodsSku(goodsItemParams);
        if (insertStatus){
            return CommonReturnType.create("","插入成功!!!");
        }else {
            return CommonReturnType.create("","插入失败!!!");
        }
    }

    @RequestMapping(value = "/isEnableSpec",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType isEnableSpec(@RequestBody GoodsEnableSpecParams enableSpecParams) throws BusinessException {
        boolean updateStatus = service.isEnableSpec(enableSpecParams);
        if (updateStatus){
            return CommonReturnType.create("","修改成功!!!");
        }else {
            return CommonReturnType.create("","修改失败!!!");
        }
    }

    @RequestMapping(value = "/selectGoods",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType selectGoods(@RequestBody SelectGoodsParams selectGoodsParams) throws BusinessException {
        // TODO: 2019/12/30 暂时写死商家id
        String sellerId="qiandu";
        selectGoodsParams.setSellerId(sellerId);
        List<GoodsResult> goods = service.selectGoods(selectGoodsParams);
        if (goods!=null){
            return CommonReturnType.create(goods);
        }else {
            return CommonReturnType.create("","数据为空!!!");
        }
    }

    @RequestMapping(value = "/deleteGoods",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType deleteGoods(@RequestBody BaseLongId longId) throws BusinessException {
        boolean deleteStatus = service.deleteGoods(longId);
        if (deleteStatus){
            return CommonReturnType.create("","删除成功");
        }else {
            return CommonReturnType.create("","数据为空!!!");
        }
    }

    @RequestMapping(value = "/updateGoodsInfo",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType updateGoodsInfo(@RequestBody GoodsInfoParams goodsInfoParams) throws BusinessException {
        boolean updateStatus = service.updateGodsInfo(goodsInfoParams);
        if (updateStatus){
            return CommonReturnType.create("","更新成功!!!");
        }else {
            return CommonReturnType.create("","更新失败!!!");
        }
    }

    @RequestMapping(value = "/insertAllSolr",method = RequestMethod.GET)
    public CommonReturnType updateGoodsInfo() throws BusinessException {
        service.insertSolrItem();
        return CommonReturnType.create("");
    }
}