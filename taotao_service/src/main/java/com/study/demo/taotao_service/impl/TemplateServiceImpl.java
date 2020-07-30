package com.study.demo.taotao_service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.ErrorEnum;
import com.study.demo.taotao_common.common.utils.JedisInterface;
import com.study.demo.taotao_dao.mapper.TemplateMapper;
import com.study.demo.taotao_interface.service.SpecificationService;
import com.study.demo.taotao_interface.service.TemplateService;
import com.study.demo.taotao_pojo.params.BaseId;
import com.study.demo.taotao_pojo.params.BasePage;
import com.study.demo.taotao_pojo.params.TemplateParams;
import com.study.demo.taotao_pojo.pojo.*;
import com.study.demo.taotao_pojo.result.BrandResult;
import com.study.demo.taotao_pojo.result.SpecificationResult;
import com.study.demo.taotao_pojo.utils.ValidatorImpl;
import com.study.demo.taotao_pojo.utils.ValidatorResult;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private ValidatorImpl validator;
    @Autowired
    private TemplateMapper mapper;

    @Autowired
    private SpecificationService specificationService;

    @Autowired
    private JedisInterface jedisInterface;

    @Override
    public boolean insertTemplate(TemplateParams templateParams) throws BusinessException {
        ValidatorResult result = this.validator.validator(templateParams);
        if (result.isError()){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,result.getErrorMassage());
        }
        String brandsJsonString = JSONObject.toJSONString(templateParams.getBrands());
        String specificationJsonString = JSONObject.toJSONString(templateParams.getSpecifications());
        String attrbuteItemJsonString = JSONObject.toJSONString(templateParams.getAttrbuteItems());
        TbTypeTemplate template = convertTbTypeTemplateFromJsonObject(brandsJsonString, specificationJsonString, attrbuteItemJsonString
                , templateParams.getName());
        int insertTemplateStatus = mapper.insertTemplate(template);
        return insertTemplateStatus==1;
    }

    @Override
    public List<TemplateParams> selectTemplates(BasePage basePage) throws BusinessException {
        ValidatorResult result = this.validator.validator(basePage);
        if (result.isError()){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,result.getErrorMassage());
        }
        PageHelper.startPage(basePage.getPage(),basePage.getPageSize());
        List<TbTypeTemplate> templates = mapper.selectTbTypeTemplates();
        saveToRedis();
        PageInfo<TbTypeTemplate> pageInfo = new PageInfo<>(templates);
        return convertTemplatesFromTemplate(pageInfo.getList());
    }

    /**
     * 将数据放入缓存
     */
    private void saveToRedis() throws BusinessException {
        List<TbTypeTemplate> tbTypeTemplates = mapper.selectTbTypeTemplates();
        for (TbTypeTemplate typeTemplate:tbTypeTemplates) {
            jedisInterface.hset("brends", String.valueOf(typeTemplate.getId()), typeTemplate.getBrandIds());
            BaseId baseId = new BaseId();
            baseId.setId(typeTemplate.getId());
            SpecificationResult result = specificationService.selectSpecification(baseId);
            String jsonString = JSONObject.toJSONString(result.getSpecificationOptions());
            jedisInterface.hset("specList", String.valueOf(result.getId()),jsonString);
        }
    }

    @Override
    public boolean updateTemplate(TemplateParams templateParams) throws BusinessException {
        ValidatorResult result = this.validator.validator(templateParams);
        if (result.isError()){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,result.getErrorMassage());
        }
        String brandsJsonString = JSONObject.toJSONString(templateParams.getBrands());
        String specificationJsonString = JSONObject.toJSONString(templateParams.getSpecifications());
        String attrbuteItemJsonString = JSONObject.toJSONString(templateParams.getAttrbuteItems());
        TbTypeTemplate template = convertTbTypeTemplateFromJsonObject(brandsJsonString, specificationJsonString, attrbuteItemJsonString
                , templateParams.getName(),templateParams.getId());
        int updateStatus = mapper.updateTemplate(template);
        return updateStatus==1;
    }

    @Override
    public boolean deleteTemplate(BaseId id) throws BusinessException {
        if (id.getId()==null||id.getId()<0){
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,"模板ID错误!!!");
        }
        int deleteStatus = mapper.deleteTemplate(id.getId());
        return deleteStatus>=1;
    }

    /**
     * 获取客户端数据
     * @param list 数据库数据
     * @return 数据转换
     */
    private List<TemplateParams> convertTemplatesFromTemplate(List<TbTypeTemplate> list) {
        List<TemplateParams> templateParams = new ArrayList<>();
        for (TbTypeTemplate template :list) {
            TemplateParams params = new TemplateParams();
            List<BrandResult> brands = JSONObject.parseArray(template.getBrandIds(), BrandResult.class);
            List<TbSpecification> specifications = JSONObject.parseArray(template.getSpecIds(), TbSpecification.class);
            List<CustomAttrbuteItems> customAttrbuteItems = JSONObject.parseArray(template.getCustomAttributeItems(), CustomAttrbuteItems.class);
            params.setAttrbuteItems(customAttrbuteItems);
            params.setBrands(brands);
            params.setSpecifications(specifications);
            params.setName(template.getName());
            params.setId(template.getId());
            templateParams.add(params);
        }
        return templateParams;
    }

    /**
     * 获取模板数据库数据
     * @param brandsJsonString 品牌 Json字符串
     * @param specificationJsonString 模板 Json字符串
     * @param attrbuteItemJsonString 扩展属性 Json字符串
     * @param templateName 模板名称 Json字符串
     * @return 模板数据库数据
     */
    private TbTypeTemplate convertTbTypeTemplateFromJsonObject(String brandsJsonString, String specificationJsonString, String attrbuteItemJsonString,String templateName) {
        TbTypeTemplate template = new TbTypeTemplate();
        template.setBrandIds(brandsJsonString);
        template.setName(templateName);
        template.setCustomAttributeItems(attrbuteItemJsonString);
        template.setSpecIds(specificationJsonString);
        return template;
    }

    /**
     * 获取模板数据库数据
     * @param brandsJsonString 品牌 Json字符串
     * @param specificationJsonString 模板 Json字符串
     * @param attrbuteItemJsonString 扩展属性 Json字符串
     * @param templateName 模板名称 Json字符串
     * @return 模板数据库数据
     */
    private TbTypeTemplate convertTbTypeTemplateFromJsonObject(String brandsJsonString, String specificationJsonString, String attrbuteItemJsonString,String templateName,Integer id) {
        TbTypeTemplate template = new TbTypeTemplate();
        template.setBrandIds(brandsJsonString);
        template.setName(templateName);
        template.setCustomAttributeItems(attrbuteItemJsonString);
        template.setSpecIds(specificationJsonString);
        template.setId(id);
        return template;
    }
}
