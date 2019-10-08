package com.bdilab.demo.service.impl;

import com.bdilab.demo.BizCode;
import com.bdilab.demo.base.ResultDTO;
import com.bdilab.demo.base.ResultExcutor;
import com.bdilab.demo.dao.TradeDAO;
import com.bdilab.demo.dto.DeliverStatus;
import com.bdilab.demo.dto.PayStatus;
import com.bdilab.demo.dto.TradeDTO;
import com.bdilab.demo.request.TradeRequest;
import com.bdilab.demo.service.TradeService;
import org.springframework.stereotype.Service;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description: 完成下单、支付、发货、收货确认等服务
 */
@Service
public class TradeServiceImpl implements TradeService {
    /**
     * 创建交易单
     * @param tradeRequest
     * @return
     */
    @Override
    public ResultDTO<Long> createTrade(TradeRequest tradeRequest){
        if (tradeRequest.getBizCode() == BizCode.DEAULT){

        }else if (tradeRequest.getBizCode() == BizCode.IRON_MAN){

        }else {

        }
        return null;
    }

    /**
     * 支付
     * @param tradeRequest
     * @return
     */
    @Override
    public ResultDTO<PayStatus> pay(TradeRequest tradeRequest){
        return null;
    }

    /**
     * 发货
     * @param tradeRequest
     * @return
     */
    @Override
    public ResultDTO<DeliverStatus> deliver(TradeRequest tradeRequest){
        return null;
    }

    /**
     * 收货
     * @param tradeRequest
     * @return
     */
    @Override
    public ResultDTO<Void> done(TradeRequest tradeRequest){
        return null;
    }

    /**
     * 加购
     * @param tradeRequest
     * @return
     */
    @Override
    public ResultDTO<TradeDTO> get(TradeRequest tradeRequest){
        return new ResultExcutor<TradeDTO>() {
            @Override
            public TradeDTO run(){
                TradeDAO tradeDAO = new TradeDAO();
                TradeDTO tradeDTO = tradeRequest.getTradeDTO();
                tradeDAO.insertOrUpdate(tradeDTO);
                System.out.println("加购交易: id " + tradeDTO.getId());
                return tradeDTO;
            }
        }.execute();
    }
}
