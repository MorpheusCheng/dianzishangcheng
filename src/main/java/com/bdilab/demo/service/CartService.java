package com.bdilab.demo.service;

import com.bdilab.demo.base.ResultDTO;
import com.bdilab.demo.request.AddCartRequest;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
public interface CartService {
    ResultDTO<Void> addCart(AddCartRequest addCartRequest);

}
