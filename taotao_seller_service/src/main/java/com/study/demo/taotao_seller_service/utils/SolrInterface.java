package com.study.demo.taotao_seller_service.utils;

import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SolrParams;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface SolrInterface {

    <T>UpdateResponse add(@NotNull T object);

    UpdateResponse delete(@NotBlank String id);

    SolrDocumentList query(@NotNull SolrParams params);
}
