package com.study.demo.taotao_seller_service;

import com.study.demo.taotao_dao.mapper.ItemCatMapper;
import com.study.demo.taotao_pojo.pojo.TbItem;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;

@SpringBootTest
class TaotaoSellerServiceApplicationTests {

    @Test
    void contextLoads() {
        HttpSolrClient client = new HttpSolrClient.Builder("http://192.168.25.129:9090/solr").build();
        TbItem item = new TbItem();
        try {
            item.setId(2l);
            item.setPrice(new BigDecimal(3000.01));
            item.setBrand("三星");
            item.setCategory("随便什么吧");
            item.setSellPoint("这就是一个普通的三星手机!!!");
            item.setGoodsId(1l);
            item.setTitle("商品的标题");
            item.setSeller("三星商家");
            item.setImage("商品图片Url");
            client.addBean(item);
            SolrQuery query = new SolrQuery();
            query.setQuery("*:*");
            query.setStart(0);
            query.setRows(20);
            QueryResponse respones = client.query(query);
            SolrDocumentList results = respones.getResults();
            for (SolrDocument de :
                    results) {
                System.out.println(new BigDecimal((Double) de.get("item_price")));
            }
            client.commit();
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }
    }
}
