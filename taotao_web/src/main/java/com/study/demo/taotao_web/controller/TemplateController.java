package com.study.demo.taotao_web.controller;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.CommonReturnType;
import com.study.demo.taotao_interface.service.TemplateService;
import com.study.demo.taotao_pojo.params.BaseId;
import com.study.demo.taotao_pojo.params.BasePage;
import com.study.demo.taotao_pojo.params.TemplateParams;
import com.study.demo.taotao_web.common.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/template")
public class TemplateController extends BaseController {

    @Reference
    private TemplateService service;

    @RequestMapping(value = "/insertTemplate",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType insertTemplate(@RequestBody TemplateParams templateParams) throws BusinessException {
        boolean insertTemplateStatus = service.insertTemplate(templateParams);
        if (insertTemplateStatus){
            return CommonReturnType.create("","添加成功!!!");
        }else {
            return CommonReturnType.create("","添加失败!!!");
        }
    }

    @RequestMapping(value = "/selectTemplate",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType selectTemplate(@RequestBody BasePage basePage) throws BusinessException {
        List<TemplateParams> templateParams = service.selectTemplates(basePage);
        if (templateParams!=null){
            return CommonReturnType.create(templateParams);
        }else {
            return CommonReturnType.create("","查询失败!!!");
        }
    }

    @RequestMapping(value = "/updateTemplate",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType updateTemplate(@RequestBody TemplateParams templateParams) throws BusinessException {
        boolean updateStatus = service.updateTemplate(templateParams);
        if (updateStatus){
            return CommonReturnType.create("","更新成功!!!");
        }else {
            return CommonReturnType.create("","更新失败!!!");
        }
    }

    @RequestMapping(value = "/deleteTemplate",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType deleteTemplate(@RequestBody BaseId baseId) throws BusinessException {
        boolean deleteStatus = service.deleteTemplate(baseId);
        if (deleteStatus){
            return CommonReturnType.create("","删除成功!!!");
        }else {
            return CommonReturnType.create("","删除失败!!!");
        }
    }
}