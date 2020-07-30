package com.study.demo.taotao_search_service.utils.activemq;

import com.alibaba.fastjson.JSONObject;
import com.study.demo.taotao_search_service.utils.SolrInterface;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DeleteSolrItemConsumer {

    @Autowired
    private SolrInterface solrInterface;

    @JmsListener(destination = "publish.delete.queue")
    public void deleteSolrItemConsumer(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return;
        }
        Integer[] id = JSONObject.parseObject(ids, Integer[].class);
        String[] strings = new String[id.length];
        for (int i = 0; i < id.length; i++) {
            strings[i] = id[i] + "";
        }
        solrInterface.delete(Arrays.asList(strings));
    }
}
