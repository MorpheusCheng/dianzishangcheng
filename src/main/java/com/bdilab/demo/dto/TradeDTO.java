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
public class TradeDTO extends BaseDTO {
    private List<Pair<Long,Integer>> items;
    private PayStatus payStatus;
    private List<String> PayOrders;
    private TradeStatus tradeStatus;
    private DeliverStatus deliverStatus;
    private List<String> deliverOrders;
}
