package com.study.demo.taotao_web.controller;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.CommonReturnType;
import com.study.demo.taotao_interface.service.SpecificationService;
import com.study.demo.taotao_pojo.params.BaseId;
import com.study.demo.taotao_pojo.params.BasePage;
import com.study.demo.taotao_pojo.params.SpecificationParams;
import com.study.demo.taotao_pojo.result.SpecificationResult;
import com.study.demo.taotao_web.common.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/specification")
public class SpecificationController extends BaseController {

    @Reference
    private SpecificationService specificationService;

    @RequestMapping(value = "/selectSpecifications",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType selectSpecifications(@RequestBody BasePage basePage) throws BusinessException {
        List<SpecificationResult> result = specificationService.selectSpecifications(basePage);
        if (result!=null){
            return CommonReturnType.create(result);
        }else {
            return CommonReturnType.create("","规格不存在!");
        }
    }

    @RequestMapping(value = "/insertSpecification",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType insertSpecification(@RequestBody SpecificationParams specificationParams) throws BusinessException {
        boolean insertStatus = specificationService.insertSpecification(specificationParams);
        if (insertStatus){
            return CommonReturnType.create("","添加成功!!!");
        }else {
            return CommonReturnType.create("","添加失败!!!");
        }
    }


    @RequestMapping(value = "/updateSpecification",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType updateSpecification(@RequestBody SpecificationParams specificationParams) throws BusinessException {
        boolean updateStatus = specificationService.updateSpecification(specificationParams);
        if (updateStatus){
            return CommonReturnType.create("","修改成功!!!");
        }else {
            return CommonReturnType.create("","修改失败!!!");
        }
    }

    @RequestMapping(value = "/deleteSpecification",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType deleteSpecification(@RequestBody BaseId baseId) throws BusinessException {
        boolean deleteStatus = specificationService.deleteSpecification(baseId);
        if (deleteStatus){
            return CommonReturnType.create("","删除成功!!!");
        }else {
            return CommonReturnType.create("","删除失败!!!");
        }
    }

    @RequestMapping(value = "selectSpecification",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType selectSpecification(@RequestBody BaseId baseId) throws BusinessException {
        SpecificationResult specification = specificationService.selectSpecification(baseId);
        if (specification!=null){
            return CommonReturnType.create(specification);
        }else {
            return CommonReturnType.create("","未查找到对应数据!!!");
        }
    }
}