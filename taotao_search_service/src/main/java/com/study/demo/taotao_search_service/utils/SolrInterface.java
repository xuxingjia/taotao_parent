package com.study.demo.taotao_search_service.utils;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface SolrInterface {

    UpdateResponse add(@NotNull Object object);

    UpdateResponse delete(@NotBlank String id);

    UpdateResponse delete(@NotBlank List<String> id);

    <T> List<T> query(@NotNull SolrParams params,Class<T> tClass);


    QueryResponse query(@NotNull SolrParams params);

    List<String> queryGroup(@NotNull SolrParams params);


    UpdateResponse add(@NotNull SolrInputDocument solrInputDocument);
}
