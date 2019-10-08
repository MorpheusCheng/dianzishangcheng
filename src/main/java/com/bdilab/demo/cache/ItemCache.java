package com.bdilab.demo.cache;

import org.springframework.stereotype.Component;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
@Component
public class ItemCache extends BaseCache {
    @Override
    public String getIdString(Long id){
        return "ITEM" + id;
    }
}
