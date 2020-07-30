package com.study.demo.taotao_user_web.controller;

import com.study.demo.taotao_common.common.CommonReturnType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

//    @RequestMapping(value = "/userLogin",method = RequestMethod.POST,consumes = "application/json")
    @GetMapping("/userLogin")
    public CommonReturnType userLogin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object details = authentication.getDetails();
        Object credentials = authentication.getCredentials();
        String name = authentication.getName();
        return CommonReturnType.create(name);
    }
}