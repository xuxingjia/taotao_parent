package com.study.demo.taotao_shop_web.controller;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.CommonReturnType;
import com.study.demo.taotao_interface.service.ItemCatService;
import com.study.demo.taotao_pojo.params.BaseId;
import com.study.demo.taotao_pojo.params.ItemCatParams;
import com.study.demo.taotao_pojo.params.ItemParams;
import com.study.demo.taotao_pojo.pojo.TbItemCat;
import com.study.demo.taotao_shop_web.common.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemCatController")
public class ItemCatController extends BaseController {
    @Reference
    private ItemCatService service;

    @RequestMapping(value = "/selectTbItemCatByParentId",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType selectTbItemCatByParentId(@RequestBody ItemCatParams params) throws BusinessException {
        List<TbItemCat> itemCats = service.selectTbItemCatByParentId(params);
        if (itemCats==null){
            return CommonReturnType.create("","商品分类管理数据为空!!!");
        }else {
            return CommonReturnType.create(itemCats);
        }
    }

    @RequestMapping(value = "/insertTbItemCat",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType insertTbItemCat(@RequestBody TbItemCat itemCat) throws BusinessException {
        boolean insertStatus = service.insertTbItemCat(itemCat);
        if (insertStatus){
            return CommonReturnType.create("","插入成功!!");
        }else {
            return CommonReturnType.create("","插入失败!!");
        }
    }

    @RequestMapping(value = "/updateItemCat",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType updateItemCat(@RequestBody TbItemCat itemCat) throws BusinessException {
        boolean insertStatus = service.updateItemCat(itemCat);
        if (insertStatus){
            return CommonReturnType.create("","更新成功!!");
        }else {
            return CommonReturnType.create("","更新失败!!");
        }
    }

    @RequestMapping(value = "/deleteItemCat",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType deleteItemCat(@RequestBody BaseId baseId) throws BusinessException {
        boolean deleteStatus = service.deleteItemCat(baseId);
        if (deleteStatus){
            return CommonReturnType.create("","删除成功!!");
        }else {
            return CommonReturnType.create("","删除失败!!");
        }
    }

    @RequestMapping(value = "/updateTbItemStatus",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType updateTbItemStatus(@RequestBody ItemParams itemParams) throws BusinessException {
        boolean deleteStatus = service.updateTbItemStatus(itemParams);
        if (deleteStatus){
            return CommonReturnType.create("","修改成功!!");
        }else {
            return CommonReturnType.create("","修改失败!!");
        }
    }
}
