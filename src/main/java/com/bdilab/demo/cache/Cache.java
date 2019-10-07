package com.bdilab.demo.cache;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
public class Cache {
    public static final Map<String,Object> cache = Maps.newConcurrentMap();
}
