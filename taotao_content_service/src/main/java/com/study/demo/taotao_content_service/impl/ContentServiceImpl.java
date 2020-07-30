package com.study.demo.taotao_content_service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.ErrorEnum;
import com.study.demo.taotao_common.common.utils.JedisInterface;
import com.study.demo.taotao_content_interface.service.ContentService;
import com.study.demo.taotao_dao.mapper.ContentMapper;
import com.study.demo.taotao_pojo.params.BaseId;
import com.study.demo.taotao_pojo.params.ContentCategoryParams;
import com.study.demo.taotao_pojo.params.UpdateContentParams;
import com.study.demo.taotao_pojo.pojo.TbContent;
import com.study.demo.taotao_pojo.pojo.TbContentCategory;
import com.study.demo.taotao_pojo.utils.ValidatorImpl;
import com.study.demo.taotao_pojo.utils.ValidatorResult;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private JedisInterface jedis;

    @Override
    public List<TbContentCategory> selectContentCategorys() {
        return contentMapper.selectContentCategorys();
    }

    @Override
    public boolean insertContent(TbContent content) throws BusinessException {
        ValidatorResult result = this.validator.validator(content);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        int insertStatus = contentMapper.insertContent(content);
        if (insertStatus == 1) {
            jedis.hDel("content", String.valueOf(content.getCategoryId()));
            List<TbContent> contents = contentMapper.selectContentByCategoryId(Math.toIntExact(content.getCategoryId()));
            String jsonString = JSONObject.toJSONString(contents);
            jedis.hset("content", String.valueOf(content.getCategoryId()), jsonString);
        }
        return insertStatus == 1;
    }

    @Override
    public boolean deleteContent(BaseId id) throws BusinessException {
        if (id.getId() <= 0) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, "广告ID错误!!!");
        }
        //获取广告类型
        TbContent content = contentMapper.selectContentById(id.getId());
        jedis.del("content" + content.getCategoryId());
        int deleteStatus = contentMapper.deleteContent(id.getId());
        if (1 == deleteStatus) {
            jedis.hDel("content", String.valueOf(content.getCategoryId()));
            List<TbContent> contents = contentMapper.selectContentByCategoryId(Math.toIntExact(content.getCategoryId()));
            String jsonString = JSONObject.toJSONString(contents);
            jedis.hset("content", String.valueOf(content.getCategoryId()), jsonString);
        }
        return deleteStatus == 1;
    }

    @Override
    public boolean updateContent(UpdateContentParams content) throws BusinessException {
        ValidatorResult result = this.validator.validator(content);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        //获取广告类型
        TbContent tbContent = contentMapper.selectContentById(Math.toIntExact(content.getId()));
        int updateStatus = contentMapper.updateContent(content);
        if (updateStatus == 1) {
            jedis.hDel("content", String.valueOf(tbContent.getCategoryId()));
            if (tbContent.getCategoryId() != content.getCategoryId()) {
                jedis.hDel("content", String.valueOf(content.getCategoryId()));
            }
        }
        return updateStatus == 1;
    }

    @Override
    public List<TbContent> selectContentByCategoryId(ContentCategoryParams params) throws BusinessException {
        ValidatorResult result = this.validator.validator(params);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        List<TbContent> contents;
        boolean exists = jedis.hExists("content", String.valueOf(params.getCategoryId()));
        if (!exists) {
            PageHelper.startPage(params.getPage(), params.getPageSize());
            List<TbContent> tbContents = contentMapper.selectContentByCategoryId(params.getCategoryId());
            PageInfo<TbContent> pageInfo = new PageInfo<>(tbContents);
            contents = pageInfo.getList();
            jedis.hset("content", String.valueOf(params.getCategoryId()), JSONObject.toJSONString(tbContents));
        } else {
            String json = jedis.hget("content", String.valueOf(params.getCategoryId()));
            contents = JSONObject.parseArray(json, TbContent.class);
        }
        return contents;
    }
}