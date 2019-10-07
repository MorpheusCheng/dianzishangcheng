package com.bdilab.demo.service;

import com.bdilab.demo.base.ResultDTO;
import com.bdilab.demo.dto.DeliverStatus;
import com.bdilab.demo.dto.PayStatus;
import com.bdilab.demo.dto.TradeDTO;
import com.bdilab.demo.request.TradeRequest;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
public interface TradeService {
    //实现下单、支付、发货、收货确认等服务
    ResultDTO<Long> createTrade(TradeRequest tradeRequest);

    ResultDTO<PayStatus> pay(TradeRequest tradeRequest);

    ResultDTO<DeliverStatus> deliver(TradeRequest tradeRequest);

    ResultDTO<Void> done(TradeRequest tradeRequest);

    ResultDTO<TradeDTO> get(TradeRequest tradeRequest);
}
