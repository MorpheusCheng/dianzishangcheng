package com.bdilab.demo.service.impl;

import com.bdilab.demo.base.ResultDTO;
import com.bdilab.demo.base.ResultExcutor;
import com.bdilab.demo.request.AddCartRequest;
import com.bdilab.demo.service.CartService;
import org.springframework.stereotype.Service;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
@Service
public class CartServiceImpl implements CartService {
    @Override
    public ResultDTO<Void> addCart(AddCartRequest addCartRequest){
        return new ResultExcutor<Void>(){
            @Override
            public Void run(){
                System.out.println("cart service");
                return null;
            }
        }.execute();
    }
}
