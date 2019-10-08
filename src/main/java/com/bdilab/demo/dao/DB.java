package com.bdilab.demo.dao;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description: 一个数据库的抽象 假设可以存储购物车、商品、交易 所有的东西都存储在一个map中
 */
public class DB {
    public static final Map<String,Object> db = Maps.newConcurrentMap();
}
