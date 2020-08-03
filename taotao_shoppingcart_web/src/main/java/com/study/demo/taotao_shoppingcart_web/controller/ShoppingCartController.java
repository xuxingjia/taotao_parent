package com.study.demo.taotao_shoppingcart_web.controller;

import com.study.demo.taotao_common.common.CommonReturnType;
import com.study.demo.taotao_shoppingcart_interface.service.ShoppingCartService;
import com.study.demo.taotao_shoppingcart_web.common.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController extends BaseController {

    @Reference
    private ShoppingCartService shoppingCartService;

    @RequestMapping(value = "/addShoppingCart",method = RequestMethod.POST)
    public CommonReturnType addShoppingCart(@CookieValue("shoppingCart") String sessionId){
        return CommonReturnType.create(sessionId);
    }
}