package com.bdilab.demo.dao;

import org.springframework.stereotype.Component;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
@Component
public class CartDAO extends BaseDAO{
    @Override
    public String getIdString(Long id){
        return "CART" + id;
    }
}
