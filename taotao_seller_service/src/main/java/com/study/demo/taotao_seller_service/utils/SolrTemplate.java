package com.study.demo.taotao_seller_service.utils;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

@Service
public class SolrTemplate implements SolrInterface {

    @Autowired
    private HttpSolrClient client;

    @Override
    public<T> UpdateResponse add(T obj) {
        try {
            if (obj instanceof List){
                UpdateResponse updateResponse = client.addBeans((Collection<?>) obj);
//                client.commit();
                return updateResponse;
            }else {
                UpdateResponse updateResponse = client.addBean(obj);
                client.commit();
                return updateResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UpdateResponse delete(@NotBlank String id) {
        try {
            UpdateResponse updateResponse = client.deleteById(id);
            client.commit();
            return updateResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SolrDocumentList query(SolrParams params) {
        try {
            QueryResponse query = client.query(params);
            return query.getResults();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
