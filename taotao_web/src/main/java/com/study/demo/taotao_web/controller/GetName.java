package com.study.demo.taotao_web.controller;

import com.study.demo.taotao_interface.service.Name;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/name")
public class GetName {

    @Reference
    private Name name;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String getName(){
        return name.getName();
    }
}