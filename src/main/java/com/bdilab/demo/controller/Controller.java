package com.bdilab.demo.controller;

import com.bdilab.demo.request.AddCartRequest;
import com.bdilab.demo.service.CartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
@RestController
public class Controller {
    @Resource
    private CartService cartService;

    @RequestMapping(value = "/")
    public String index(){
        AddCartRequest addCartRequest = new AddCartRequest();

        addCartRequest.setCount(1);
        cartService.addCart(addCartRequest);

        return "hello";
    }
}
