package com.bdilab.demo.cache;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
public class ItemCache extends BaseCache {
    @Override
    public String getIdString(Long id){
        return "ITEM" + id;
    }
}
