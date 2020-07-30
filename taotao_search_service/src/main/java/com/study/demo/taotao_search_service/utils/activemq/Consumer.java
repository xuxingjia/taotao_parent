package com.study.demo.taotao_search_service.utils.activemq;

import com.alibaba.fastjson.JSONObject;
import com.study.demo.taotao_pojo.pojo.TbItem;
import com.study.demo.taotao_search_service.utils.SolrInterface;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Consumer {

    @Autowired
    private SolrInterface solrInterface;

    @JmsListener(destination = "publish.queue")
    public void receiveQueue(String json) {
        if (StringUtils.isEmpty(json)){
            return;
        }
        List<TbItem> items = JSONObject.parseArray(json, TbItem.class);
        UpdateResponse response = solrInterface.add(items);
    }
}
