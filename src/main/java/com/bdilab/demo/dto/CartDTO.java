package com.bdilab.demo.dto;

import javafx.util.Pair;
import lombok.Data;

import java.util.List;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
@Data
public class CartDTO extends BaseDTO {
    private List<Pair<Long,Integer>> items;
}
