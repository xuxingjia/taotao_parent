package com.study.demo.taotao_seller_service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.ErrorEnum;
import com.study.demo.taotao_dao.mapper.*;
import com.study.demo.taotao_interface.service.GoodsService;
import com.study.demo.taotao_pojo.dao.GoodsAtrributeDao;
import com.study.demo.taotao_pojo.dao.GoodsAttributeItemsDao;
import com.study.demo.taotao_pojo.dao.GoodsSpecificationDao;
import com.study.demo.taotao_pojo.params.*;
import com.study.demo.taotao_pojo.pojo.*;
import com.study.demo.taotao_pojo.result.GoodsAttributeItemsResult;
import com.study.demo.taotao_pojo.result.GoodsResult;
import com.study.demo.taotao_pojo.result.GoodsSpecificationResult;
import com.study.demo.taotao_pojo.utils.ValidatorImpl;
import com.study.demo.taotao_pojo.utils.ValidatorResult;
import com.study.demo.taotao_seller_service.utils.SolrInterface;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private GoodsMapper mapper;

    @Autowired
    private ItemCatMapper catMapper;

    @Autowired
    private TemplateMapper templateMapper;
    @Autowired
    private SpecificationMapper specificationMapper;

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private SolrInterface client;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseStrId insertGoodsInfo(GoodsInfoParams goodsInfoParams) throws BusinessException {
        ValidatorResult result = this.validator.validator(goodsInfoParams);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        TbGoods goods = convertTbGoodsFromGoodsInfoParams(goodsInfoParams);
        int goodsStatus = mapper.insertGoods(goods);
        TbGoodsDesc goodsDesc = convertTbGoodsDescFromGoodsInfoParams(goodsInfoParams);
        goodsDesc.setGoodsId(goods.getId());
        int goodsDescStatus = mapper.insertGoodsDesc(goodsDesc);
        BaseStrId baseStrId = new BaseStrId();
        if (goodsStatus == 1 && goodsDescStatus == 1) {
            baseStrId.setId(String.valueOf(goods.getId()));
        } else {
            baseStrId.setId("");
        }
        return baseStrId;
    }

    @Override
    public List<GoodsAttributeItemsResult> selectGoodsAttributeItems(BaseId baseId) throws BusinessException {
        Integer id = baseId.getId();
        if (id == null || id < 0) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, "商品模板ID异常!!!");
        }
        String attributeItems = mapper.selectGoodsAttributeItems(id);
        return JSONObject.parseArray(attributeItems, GoodsAttributeItemsResult.class);
    }

    @Override
    public boolean insertGoodsAttributeItems(GoodsAttributeItemsParams params) throws BusinessException {
        ValidatorResult result = this.validator.validator(params);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        List<GoodsAttributeItems> items = params.getItems();
        for (GoodsAttributeItems item : items) {
            ValidatorResult validatorResult = this.validator.validator(item);
            if (validatorResult.isError()) {
                throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, validatorResult.getErrorMassage());
            }
        }
        GoodsAttributeItemsDao itemsDao = convertGoodsAttributeFromParams(params);
        itemsDao.setId(params.getId());
        int insertStatus = mapper.insertGoodsAttributeItems(itemsDao);
        return insertStatus == 1;
    }

    @Override
    public boolean deleteGoodsImage(BaseId id) throws BusinessException {
        if (id.getId() == null || id.getId() < 0) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, "商品ID错误!!!");
        }
        TbGoodsImage goodsImage = mapper.selectGoodsImage(id.getId());
        if (goodsImage == null) {
            throw new BusinessException(ErrorEnum.RESULT_ISEMPTY, "图片不存在!!!");
        }
        int deleteStatus = mapper.deleteGoodsImageById(id.getId());
        if (deleteStatus == 1) {
            client.delete(String.valueOf(id.getId()));
        }
        return deleteStatus == 1;
    }

    @Override
    public List<GoodsSpecificationResult> selectGoodsSpecification(@NotNull(message = "商品ID不能为空") @Min(value = 0, message = "商品ID不能小于0") Long id) throws BusinessException {
        ValidatorResult result = this.validator.validator(id);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        GoodsSpecificationDao goodsSpecificationDao = mapper.selectGoodsSpecificaton(id);
        Integer typeId = null;
        if (goodsSpecificationDao.getCategory3Id() != null && goodsSpecificationDao.getCategory3Id() >= 0) {
            typeId = catMapper.selectTypeId(goodsSpecificationDao.getCategory3Id());
        } else if (goodsSpecificationDao.getCategory2Id() != null && goodsSpecificationDao.getCategory2Id() >= 0) {
            typeId = catMapper.selectTypeId(goodsSpecificationDao.getCategory2Id());
        } else if (goodsSpecificationDao.getCategory1Id() != null && goodsSpecificationDao.getCategory1Id() >= 0) {
            typeId = catMapper.selectTypeId(goodsSpecificationDao.getCategory1Id());
        }
        String specIds = templateMapper.selectSpecIds(typeId);
        List<TbSpecification> specifications = JSONObject.parseArray(specIds, TbSpecification.class);
        ArrayList<GoodsSpecificationResult> results = new ArrayList<>();
        for (TbSpecification specificaton : specifications) {
            List<TbSpecificationOption> specificationOptions = specificationMapper.selectSpecificationOptionBySpecId(specificaton.getId());
            GoodsSpecificationResult specificationResult = new GoodsSpecificationResult();
            specificationResult.setSpecification(specificaton);
            specificationResult.setSpecificationOptions(specificationOptions);
            results.add(specificationResult);
        }
        return results;
    }

    @Override
    public boolean insertGoodsSku(GoodsItemParams goodsItemParams) throws BusinessException {
        ValidatorResult result = this.validator.validator(goodsItemParams);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        TbItem item = convertTbItemFromGoodsItemsParams(goodsItemParams);
        int insertStatus = mapper.insertGoodsSku(item);
        if (insertStatus == 1) {
            client.add(item);
        }
        return insertStatus == 1;
    }

    @Override
    public boolean isEnableSpec(GoodsEnableSpecParams enableSpecParams) throws BusinessException {
        ValidatorResult result = this.validator.validator(enableSpecParams);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        int updateStatus = mapper.updateGoodsIsEnableSpecById(enableSpecParams);
        return updateStatus == 1;
    }

    @Override
    public List<GoodsResult> selectGoods(SelectGoodsParams goodsParams) throws BusinessException {
        ValidatorResult result = this.validator.validator(goodsParams);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        ValidatorResult pageResult = this.validator.validator(goodsParams);
        if (pageResult.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, pageResult.getErrorMassage());
        }
        PageHelper.startPage(goodsParams.getPage(), goodsParams.getPageSize());
        List<TbGoods> goods = mapper.selectGoodsBySellerId(goodsParams);
        List<GoodsResult> goodsResults = convertGoodsResultFromTbGoods(goods);
        PageInfo<GoodsResult> pageInfo = new PageInfo<>(goodsResults);
        return pageInfo.getList();
    }

    @Override
    public boolean deleteGoods(BaseLongId baseLongId) throws BusinessException {
        Long id = baseLongId.getId();
        if (id == null || id < 0) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, "商品id错误!!!");
        }
        int deleteStatus = mapper.deleteGoods(baseLongId.getId());
        return deleteStatus == 1;
    }

    @Override
    public boolean updateGodsInfo(GoodsInfoParams goodsInfoParams) throws BusinessException {
        ValidatorResult result = this.validator.validator(goodsInfoParams);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        if (goodsInfoParams.getId() == null || goodsInfoParams.getId() < 0) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, "商品ID错误!!!");
        }
        TbGoods goods = convertTbGoodsFromGoodsInfoParams(goodsInfoParams);
        int updateStatus = mapper.updateGoodsInfo(goods);
        return updateStatus == 1;
    }

    @Override
    public List<TbGoodsImage> selectGoodsImage(BaseLongId longId) throws BusinessException {
        if (longId.getId() == null || longId.getId() < 0) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, "商品ID错误!!!");
        }
        List<TbGoodsImage> goodsImages = mapper.selectGoodsImageByGoodsId(longId.getId());
        return goodsImages;
    }

    @Override
    public void insertSolrItem() {
        List<TbItem> tbItems = itemCatMapper.insertSolrAllItem();
        client.add(tbItems);
    }

    /**
     * 转换数据
     *
     * @param goods 商品列表
     * @return 前端列表数据
     */
    private List<GoodsResult> convertGoodsResultFromTbGoods(List<TbGoods> goods) {
        ArrayList<GoodsResult> goodsResults = new ArrayList<>();
        for (TbGoods good :
                goods) {
            GoodsResult result = new GoodsResult();
            BeanUtils.copyProperties(good, result);
            if (good.getCategory3Id() != null && good.getCategory3Id() > 0) {
                String category1 = itemCatMapper.selectCategoryById(good.getCategory1Id());
                result.setCategory1Id(category1);
            } else if (good.getCategory2Id() != null && good.getCategory2Id() > 0) {
                String category2 = itemCatMapper.selectCategoryById(good.getCategory2Id());
                result.setCategory2Id(category2);
            } else if (good.getCategory1Id() != null && good.getCategory1Id() > 0) {
                String category3 = itemCatMapper.selectCategoryById(good.getCategory3Id());
                result.setCategory3Id(category3);
            }
            goodsResults.add(result);
        }
        return goodsResults;
    }

    /**
     * 获取商品规格信息对应数据库
     *
     * @param goodsItemParams 前端上传的商品信息
     * @return 商品规格信息对象数据库的
     */
    private TbItem convertTbItemFromGoodsItemsParams(GoodsItemParams goodsItemParams) {
        TbItem item = new TbItem();
        item.setNum(goodsItemParams.getNum());
        item.setIsDefault(goodsItemParams.getIsDefault());
        item.setPrice(goodsItemParams.getPrice());
        item.setStatus(String.valueOf(goodsItemParams.getStatus()));
        String json = JSONObject.toJSONString(goodsItemParams.getValues());
        item.setSpec(json);
        item.setGoodsId(Long.valueOf(goodsItemParams.getId()));
        GoodsAtrributeDao goodsAtrributeDao = mapper.selectGoodsAttribute(Long.valueOf(goodsItemParams.getId()));
        item.setSellerId(goodsAtrributeDao.getSellerId());
        String sellerName = sellerMapper.selectSellerName(goodsAtrributeDao.getSellerId());
        if (StringUtils.isEmpty(sellerName)) {
            sellerName = "";
        }
        item.setSeller(sellerName);
        StringBuilder title = new StringBuilder(sellerName);
        if (goodsItemParams.getValues() != null) {
            for (GoodsSpecValue value :
                    goodsItemParams.getValues()) {
                title.append(" ").append(value.getSpecValue());
            }
        }
        item.setTitle(title.toString());
        List<TbGoodsImage> goodsImages = mapper.selectGoodsImageByGoodsId(Long.valueOf(goodsItemParams.getId()));
        if (goodsImages != null) {
            if (goodsImages.size() > 0) {
                item.setImage(goodsImages.get(0).getUrl());
            }
        }
        Integer categoryId = -1;
        if (goodsAtrributeDao.getCategory3Id() != null && goodsAtrributeDao.getCategory3Id() > 0) {
            categoryId = goodsAtrributeDao.getCategory3Id();
        } else if (goodsAtrributeDao.getCategory2Id() != null && goodsAtrributeDao.getCategory2Id() > 0) {
            categoryId = goodsAtrributeDao.getCategory2Id();
        } else if (goodsAtrributeDao.getCategory1Id() != null && goodsAtrributeDao.getCategory1Id() > 0) {
            categoryId = goodsAtrributeDao.getCategory1Id();
        }
        item.setCategoryId(categoryId);
        String category = itemCatMapper.selectCategoryById(categoryId);
        item.setCategory(category);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd hh:mm:ss");
        String format = dateFormat.format(new Date());
        item.setCreateTime(Timestamp.valueOf(format));
        item.setUpdateTime(Timestamp.valueOf(format));
        String brandName = brandMapper.selectBrandNameById(goodsAtrributeDao.getBrandId());
        item.setBrand(brandName);
        return item;
    }

    /**
     * 获取扩展属性的JSON字符串
     *
     * @param params 扩展属性
     * @return 商品数据库扩展数据
     */
    private GoodsAttributeItemsDao convertGoodsAttributeFromParams(GoodsAttributeItemsParams params) {
        GoodsAttributeItemsDao dao = new GoodsAttributeItemsDao();
        String jsonString = JSONObject.toJSONString(params.getItems());
        dao.setJosnString(jsonString);
        return dao;
    }

    /**
     * 获取goodsdesc数据
     *
     * @param goodsInfoParams 商品详情数据
     * @return goodsdescs 数据库数据
     */
    private TbGoodsDesc convertTbGoodsDescFromGoodsInfoParams(GoodsInfoParams goodsInfoParams) {
        TbGoodsDesc goodsDesc = new TbGoodsDesc();
        BeanUtils.copyProperties(goodsInfoParams, goodsDesc);
        return goodsDesc;
    }

    /**
     * 获取商品数据库数据
     *
     * @param goodsInfoParams 商品详情数据
     * @return 商品数据库数据
     */
    private TbGoods convertTbGoodsFromGoodsInfoParams(GoodsInfoParams goodsInfoParams) {
        TbGoods goods = new TbGoods();
        BeanUtils.copyProperties(goodsInfoParams, goods);
        if (goodsInfoParams.getId() == null || goodsInfoParams.getId() < 0) {
            goods.setAuditStatus("0");
            // TODO: 2019/12/25 暂时先写死阿里巴巴   这个是从登录账号中获取的
            goods.setSellerId("alibaba");
            goods.setIsDelete("1");
        }
        return goods;
    }
}
