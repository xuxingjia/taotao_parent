package com.study.demo.taotao_web.controller;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_interface.service.BrandService;
import com.study.demo.taotao_pojo.params.BasePage;
import com.study.demo.taotao_pojo.pojo.TbBrand;
import com.study.demo.taotao_web.common.BaseController;
import com.study.demo.taotao_common.common.CommonReturnType;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController extends BaseController {

    @Reference
    private BrandService brandService;

    @RequestMapping(value = "/getAllBrand",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType getAllBrand(@RequestBody BasePage basePage) throws BusinessException {
        List<TbBrand> allBrand = brandService.getAllBrand(basePage);
        if (allBrand!=null){
            return CommonReturnType.create(allBrand);
        }else {
            return CommonReturnType.create("");
        }
    }

    @RequestMapping(value = "/selectBrandById",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType selectBrandById(@RequestBody TbBrand brand) throws BusinessException {
        TbBrand brandInfo = brandService.getBrandById(brand.getId());
        return CommonReturnType.create(brandInfo);
    }

    @RequestMapping(value = "/updateBrandById",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType updateBrandById(@RequestBody TbBrand brand) throws BusinessException {
        boolean updateResult = brandService.updateBrandById(brand);
        if (updateResult){
            return CommonReturnType.create(brandService.getBrandById(brand.getId()),"修改成功!");
        }else {
            return CommonReturnType.create("","修改失败!");
        }
    }

    @RequestMapping(value = "/deleteBrandById",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType deleteBrandById(@RequestBody TbBrand brand) throws BusinessException {
        boolean deleteResult = brandService.deleteBrandById(brand.getId());
        if (deleteResult){
            return CommonReturnType.create("","修改成功!");
        }else {
            return CommonReturnType.create("","修改失败!");
        }
    }

    @RequestMapping(value = "/insertBrand",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType insertBrand(@RequestBody TbBrand brand) throws BusinessException {
        TbBrand insertBrand = brandService.insertBrand(brand);
        if (insertBrand!=null){
            return CommonReturnType.create(insertBrand,"修改成功!");
        }else {
            return CommonReturnType.create("","修改失败!");
        }
    }
}