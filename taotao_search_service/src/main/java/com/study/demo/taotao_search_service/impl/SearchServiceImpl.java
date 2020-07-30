package com.study.demo.taotao_search_service.impl;

import com.alibaba.fastjson.JSONObject;
import com.study.demo.taotao_common.common.utils.JedisInterface;
import com.study.demo.taotao_pojo.pojo.*;
import com.study.demo.taotao_search_interface.service.SearchService;
import com.study.demo.taotao_search_service.utils.SolrInterface;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.GroupParams;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SolrInterface solrInterface;

    @Autowired(required = true)
    private JedisInterface jedisInterface;

    @Override
    public Map search(Map<String, String> keyWords) {
        HashMap<String, List> map = new HashMap<>();
        List<String> items = searchQuery(keyWords);
        String categoryName = keyWords.get("categoryName");
        List<TbBrand> tbBrands;
        List<TbSpecificationOption> specifications;
        List<Map> itemList;
        if (StringUtils.isEmpty(categoryName)) {
            tbBrands = searchBrand(items.get(0));
            specifications = searchSpec(items.get(0));
            itemList = searchGoods(items.get(0));
        } else {
            tbBrands = searchBrand(categoryName);
            specifications = searchSpec(categoryName);
            itemList = searchGoods(categoryName);
        }
        String priceParams = keyWords.get("priceParams");
        if (!StringUtils.isEmpty(priceParams)) {
            String[] split = priceParams.split("=");
            itemList = searchGoods(categoryName, split);
        }
        map.put("category", items);
        map.put("specifications", specifications);
        map.put("brands", tbBrands);
        map.put("goods", itemList);
        return map;
    }

    /**
     * @param categoryName 商品分类数据
     * @return 在solr中搜索商品数据
     */
    private List<Map> searchGoods(String categoryName, String[] split) {
        List<Map> list = new ArrayList<>();
        SolrQuery solrQuery = new SolrQuery();
        if (split!= null) {
            solrQuery.set("fq","item_price:["+split[0]+" TO "+split[1]+"]");
            solrQuery.addSort("item_price", SolrQuery.ORDER.asc);
        }
        solrQuery.set("df", "item_keywords");
        solrQuery.setStart(0);
        solrQuery.setRows(10);
        solrQuery.set("q", categoryName);
        QueryResponse response = solrInterface.query(solrQuery);
        SolrDocumentList results = response.getResults();
        for (SolrDocument document : results) {
            Map map = document.toMap(new HashMap<>());
            list.add(map);
        }
        return list;
    }

    /**
     * @param categoryName 商品分类数据
     * @return 在solr中搜索商品数据
     */
    private List<Map> searchGoods(@NotBlank String categoryName) {
        List<Map> list = new ArrayList<>();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("df", "item_keywords");
        solrQuery.setStart(0);
        solrQuery.setRows(10);
        solrQuery.set("q", categoryName);
        QueryResponse response = solrInterface.query(solrQuery);
        SolrDocumentList results = response.getResults();
        for (SolrDocument document : results) {
            Map map = document.toMap(new HashMap<>());
            list.add(map);
        }
        return list;
    }


    /**
     * 根据分类ID 获取Spec 数据
     *
     * @param brandKeyWorld 查询数据
     * @return Spec数据
     */
    private List<TbSpecificationOption> searchSpec(String brandKeyWorld) {
        String brandKeyId = jedisInterface.hget("itemCat", brandKeyWorld);
        if (StringUtils.isEmpty(brandKeyId)) {
            return null;
        }
        String specList = jedisInterface.hget("specList", brandKeyId);
        if (!StringUtils.isEmpty(specList)) {
            return JSONObject.parseArray(specList, TbSpecificationOption.class);
        }
        return null;
    }

    /**
     * 更具条件查询品牌信息
     *
     * @param brandKeyWorld 查询信息
     */
    private List<TbBrand> searchBrand(String brandKeyWorld) {
        String brandKeyId = jedisInterface.hget("itemCat", brandKeyWorld);
        if (StringUtils.isEmpty(brandKeyId)) {
            return null;
        }
        String itemCat = jedisInterface.hget("brends", brandKeyId);
        if (itemCat != null) {
            return JSONObject.parseArray(itemCat, TbBrand.class);
        }
        return null;
    }

    /**
     * 根据条件分组查询分类信息
     *
     * @param keyWords 查询条件
     * @return 分类信息
     */
    private List<String> searchQuery(Map<String, String> keyWords) {
        SolrQuery query = new SolrQuery();
        query.setParam(GroupParams.GROUP, true);
        query.setParam(GroupParams.GROUP_FIELD, "item_category");
        query.setQuery(keyWords.get("keyword"));
        query.set("fl", "item_category");
        query.set("df", "item_keywords");
        return solrInterface.queryGroup(query);
    }
}