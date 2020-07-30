package com.study.demo.taotao_seller_service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.ErrorEnum;
import com.study.demo.taotao_common.common.utils.JedisInterface;
import com.study.demo.taotao_dao.mapper.ItemCatMapper;
import com.study.demo.taotao_interface.service.ItemCatService;
import com.study.demo.taotao_pojo.params.BaseId;
import com.study.demo.taotao_pojo.params.ItemCatParams;
import com.study.demo.taotao_pojo.params.ItemParams;
import com.study.demo.taotao_pojo.pojo.TbItem;
import com.study.demo.taotao_pojo.pojo.TbItemCat;
import com.study.demo.taotao_pojo.utils.ValidatorImpl;
import com.study.demo.taotao_pojo.utils.ValidatorResult;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemCatMapper mapper;

    @Autowired
    private JedisInterface jedisInterface;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "queue")
    private Destination queue;

    @Resource(name = "deleteSolrQueue")
    private Destination deleteSolrQueue;


    @Override
    public List<TbItemCat> selectTbItemCatByParentId(ItemCatParams params) throws BusinessException {
        ValidatorResult result = this.validator.validator(params);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        PageHelper.startPage(params.getPage(), params.getPageSize());
        List<TbItemCat> itemCats = mapper.selectTbItemCatByParentId(params.getParentId());
        PageInfo<TbItemCat> pageInfo = new PageInfo<>(itemCats);
        List<TbItemCat> tbItemCats = mapper.selectAllItemCat();
        for (TbItemCat itemCat : tbItemCats) {
            jedisInterface.hset("itemCat", itemCat.getName(), String.valueOf(itemCat.getTypeId()));
        }
        return pageInfo.getList();
    }

    @Override
    public boolean insertTbItemCat(TbItemCat tbItemCat) throws BusinessException {
        ValidatorResult result = this.validator.validator(tbItemCat);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        int insertStatus = mapper.insertTbItemCat(tbItemCat);
        return insertStatus == 1;
    }

    @Override
    public boolean updateItemCat(TbItemCat itemCat) throws BusinessException {
        ValidatorResult result = this.validator.validator(itemCat);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        int updateStatus = mapper.updateItemCat(itemCat);
        return updateStatus == 1;
    }

    @Override
    public boolean deleteItemCat(BaseId baseId) throws BusinessException {
        if (baseId.getId() == null || baseId.getId() < 0) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, "商品分类ID异常!!!");
        }
        List<TbItemCat> itemCats = mapper.selectTbItemCatByParentId(baseId.getId());
        if (itemCats.size() > 0) {
            throw new BusinessException(ErrorEnum.RESULT_NOT_ISEMPTY, "此商品分类存在次级分类,不能删除!!!");
        } else {
            int deleteStatus = mapper.deleteItemCat(baseId.getId());
            return deleteStatus > 0;
        }
    }

    @Override
    public boolean updateTbItemStatus(ItemParams itemParams) throws BusinessException {
        ValidatorResult result = this.validator.validator(itemParams);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        int updateStatus = mapper.updateItemCatStatus(itemParams.getIds(), itemParams.getStatus());
        if (updateStatus >= 1) {
            if ("1".equals(itemParams.getStatus())) {
                List<TbItem> itemCats = mapper.insertSolrAllItem();
                String itemCatsJsonString = JSONObject.toJSONString(itemCats);
                jmsMessagingTemplate.convertAndSend(queue, itemCatsJsonString);
            } else if ("0".equals(itemParams.getStatus())) {
                String idsJsonString = JSONObject.toJSONString(itemParams.getIds());
                jmsMessagingTemplate.convertAndSend(deleteSolrQueue, idsJsonString);
            }
        }
        return updateStatus >= 1;
    }
}