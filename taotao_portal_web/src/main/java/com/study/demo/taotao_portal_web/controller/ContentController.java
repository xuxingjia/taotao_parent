package com.study.demo.taotao_portal_web.controller;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.CommonReturnType;
import com.study.demo.taotao_content_interface.service.ContentService;
import com.study.demo.taotao_pojo.params.BaseId;
import com.study.demo.taotao_pojo.params.ContentCategoryParams;
import com.study.demo.taotao_pojo.params.UpdateContentParams;
import com.study.demo.taotao_pojo.pojo.TbContent;
import com.study.demo.taotao_pojo.pojo.TbContentCategory;
import com.study.demo.taotao_portal_web.common.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contentController")
public class ContentController extends BaseController {

    @Reference
    private ContentService contentService;

    @RequestMapping(value = "/selectContentCategorys", method = RequestMethod.POST, consumes = "application/json")
    public CommonReturnType selectContentCategorys() {
        List<TbContentCategory> contentCategorys = contentService.selectContentCategorys();
        if (contentCategorys == null) {
            return CommonReturnType.create("", "数据为空!!!");
        } else {
            return CommonReturnType.create(contentCategorys);
        }
    }

    @RequestMapping(value = "/insertContent", method = RequestMethod.POST, consumes = "application/json")
    public CommonReturnType insertContent(@RequestBody TbContent content) throws BusinessException {
        boolean insertStatus = contentService.insertContent(content);
        if (insertStatus) {
            return CommonReturnType.create("", "插入成功!!!");
        } else {
            return CommonReturnType.create("","插入失败!!!");
        }
    }

    @RequestMapping(value = "/deleteContent", method = RequestMethod.POST, consumes = "application/json")
    public CommonReturnType deleteContent(@RequestBody BaseId id) throws BusinessException {
        boolean insertStatus = contentService.deleteContent(id);
        if (insertStatus) {
            return CommonReturnType.create("", "删除成功!!!");
        } else {
            return CommonReturnType.create("","删除失败!!!");
        }
    }

    @RequestMapping(value = "/updateContent", method = RequestMethod.POST, consumes = "application/json")
    public CommonReturnType updateContent(@RequestBody UpdateContentParams params) throws BusinessException {
        boolean updateStatus = contentService.updateContent(params);
        if (updateStatus) {
            return CommonReturnType.create("", "修改成功!!!");
        } else {
            return CommonReturnType.create("","修改失败!!!");
        }
    }

    @RequestMapping(value = "/selectContentByCategoryId", method = RequestMethod.POST, consumes = "application/json")
    public CommonReturnType selectContentByCategoryId(@RequestBody ContentCategoryParams params) throws BusinessException {
        List<TbContent> contents = contentService.selectContentByCategoryId(params);
        if (contents!=null) {
            return CommonReturnType.create(contents);
        } else {
            return CommonReturnType.create("","广告数据为空!!!");
        }
    }
}