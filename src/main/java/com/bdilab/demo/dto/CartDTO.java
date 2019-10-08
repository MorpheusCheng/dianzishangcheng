package com.bdilab.demo.dto;

import javafx.util.Pair;
import lombok.Data;

import java.util.List;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description: 购物车里存储的是商品 其中第一个应该是商品的id,第二个参数是商品的个数
 */
@Data
public class CartDTO extends BaseDTO {
    private List<Pair<Long,Integer>> items;
}
