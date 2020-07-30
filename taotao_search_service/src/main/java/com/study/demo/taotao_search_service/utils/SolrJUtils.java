package com.study.demo.taotao_search_service.utils;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:solr.properties")
public class SolrJUtils{

    @Value("${SOLR_URL}")
    private String solrUrl;

    @Bean
    public HttpSolrClient getSolrClient(){
       return  new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(6000)
               .build();
    }
}
