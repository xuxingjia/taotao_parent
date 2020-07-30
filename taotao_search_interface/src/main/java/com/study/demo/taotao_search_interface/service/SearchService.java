package com.study.demo.taotao_search_interface.service;

import java.util.Map;

public interface SearchService {

    /**
     * 查询
     * @param keyWords 搜索关键字
     * @return 结果列表
     */
    Map search(Map<String,String> keyWords);
}
