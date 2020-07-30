package com.study.demo.taotao_search_web.controller;

import com.study.demo.taotao_common.common.CommonReturnType;
import com.study.demo.taotao_search_interface.service.SearchService;
import com.study.demo.taotao_search_web.common.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/searchController")
public class SearchController extends BaseController {

    @Reference
    private SearchService searchService;

    @RequestMapping(value = "/search",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType search(@RequestBody Map<String,String> keywords){
        Map searchResult = searchService.search(keywords);
        return CommonReturnType.create(searchResult);
    }
}