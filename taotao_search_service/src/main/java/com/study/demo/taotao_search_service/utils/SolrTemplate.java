package com.study.demo.taotao_search_service.utils;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.*;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SolrTemplate implements SolrInterface {

    @Autowired
    private HttpSolrClient client;

    @Override
    public UpdateResponse add(Object obj) {
        try {
            if (obj instanceof List){
                UpdateResponse updateResponse = client.addBeans((Collection<?>) obj);
                client.commit();
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
    public UpdateResponse delete(@NotBlank List<String> ids) {
        try {
            UpdateResponse updateResponse = client.deleteById(ids);
            client.commit();
            return updateResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public<T> List<T> query(SolrParams params, Class<T> tClass) {
        try {
            QueryResponse query = client.query(params);
            return query.getBeans(tClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public QueryResponse query(SolrParams params) {
        try {
            return client.query(params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> queryGroup(@NotNull SolrParams params) {
        ArrayList<String> list = new ArrayList<>();
        try {
            QueryResponse response = client.query(params);
            GroupResponse groupResponse = response.getGroupResponse();
            List<GroupCommand> values = groupResponse.getValues();
            for (GroupCommand groups :values) {
                for (Group group :groups.getValues()) {
                    String groupValue = group.getGroupValue();
                    list.add(groupValue);
                }
            }
            return list;
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UpdateResponse add(SolrInputDocument solrInputDocumentD) {
        try {
            UpdateResponse response = client.add(solrInputDocumentD);
            client.close();
            return response;
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
