package com.bdilab.demo.dto;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description: 数据对象层 将数据库中的数据以对象的形式暴露出来 id对应的是组件的id feature对应的是
 */
public class BaseDTO {
    private Long id;
    private Map<String,String> feature;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeature(String key) {
        if (feature == null){
            return null;
        }
        return feature.get(key);
    }

    public void setFeature(Map<String, String> feature) {
        this.feature = feature;
    }

    public void putFeature(String key,String value){
        if (feature == null){
            feature = Maps.newConcurrentMap();
        }
        feature.put(key,value);
    }
}
