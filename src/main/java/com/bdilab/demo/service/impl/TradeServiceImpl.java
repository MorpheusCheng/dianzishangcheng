package com.bdilab.demo.service.impl;

import com.bdilab.demo.base.ResultDTO;
import com.bdilab.demo.dto.DeliverStatus;
import com.bdilab.demo.dto.PayStatus;
import com.bdilab.demo.dto.TradeDTO;
import com.bdilab.demo.request.TradeRequest;
import com.bdilab.demo.service.TradeService;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
public class TradeServiceImpl implements TradeService {
    //完成下单、支付、发货、收货确认等服务
    @Override
    public ResultDTO<Long> createTrade(TradeRequest tradeRequest){
        return null;
    }

    @Override
    public ResultDTO<PayStatus> pay(TradeRequest tradeRequest){
        return null;
    }

    @Override
    public ResultDTO<DeliverStatus> deliver(TradeRequest tradeRequest){
        return null;
    }

    @Override
    public ResultDTO<Void> done(TradeRequest tradeRequest){
        return null;
    }

    @Override
    public ResultDTO<TradeDTO> get(TradeRequest tradeRequest){
        return null;
    }
}
