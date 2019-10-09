package com.bdilab.demo.service.impl;

import com.bdilab.demo.BizCode;
import com.bdilab.demo.base.ResultDTO;
import com.bdilab.demo.base.ResultExcutor;
import com.bdilab.demo.dao.ItemDAO;
import com.bdilab.demo.dao.TradeDAO;
import com.bdilab.demo.dto.*;
import com.bdilab.demo.request.TradeRequest;
import com.bdilab.demo.service.TradeService;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description: 完成下单、支付、发货、收货确认等服务
 */
@Service
public class TradeServiceImpl implements TradeService {
    /**
     * 创建交易单 支付单
     * @param tradeRequest
     * @return
     */
    @Override
    public ResultDTO<Long> createTrade(TradeRequest tradeRequest){
        return new ResultExcutor<Long>(){
            @Override
            public Long run(){
                TradeDTO tradeDTO = tradeRequest.getTradeDTO();
                TradeDAO tradeDAO = new TradeDAO();
                ItemDAO itemDAO = new ItemDAO();

                Calendar calendar = Calendar.getInstance();
                int sec = calendar.get(Calendar.SECOND);

                float money = 0;
                //这个地方实际过程中得考虑一下
                for (Pair<Long ,Integer> pair : tradeDTO.getItems()){
                    ItemDTO itemDTO = (ItemDTO) itemDAO.select(pair.getKey());
                    money +=  itemDTO.getPrice() * pair.getValue();
                }

                if (tradeRequest.getBizCode() == BizCode.DEAULT){
                    tradeDTO.setTradeStatus(TradeStatus.CREATED);
                    System.out.println("默认模式 创建交易单");
                    tradeDTO.setTradeStatus(TradeStatus.PAYING);
                    System.out.println("创建支付单");
                    tradeDAO.insertOrUpdate(tradeDTO);
                    return tradeDTO.getId();
                }else if (tradeRequest.getBizCode() == BizCode.IRON_MAN){
                    if (sec != 5 || money > 50000){
                        System.out.println("钢铁侠模式 禁止创建订单");
                        return 0l;
                    }
                    tradeDTO.setTradeStatus(TradeStatus.CREATED);
                    tradeDTO.setTradeStatus(TradeStatus.PAYING);
                    tradeDAO.insertOrUpdate(tradeDTO);
                    System.out.println("钢铁侠模式创建订单");
                    return tradeDTO.getId();
                }else {
                    if ((sec * sec % 10 != 5) || money < 5000){
                        System.out.println("SPACEX模式 禁止创建订单");
                        return 0l;
                    }
                    tradeDTO.setTradeStatus(TradeStatus.CREATED);
                    tradeDTO.setTradeStatus(TradeStatus.PAYING);
                    tradeDAO.insertOrUpdate(tradeDTO);
                    System.out.println("SPACEX模式创建订单");
                    return tradeDTO.getId();
                }
            }
        }.execute();
    }

    /**
     * 支付
     * @param tradeRequest
     * @return
     */
    @Override
    public ResultDTO<PayStatus> pay(TradeRequest tradeRequest){
        return new ResultExcutor<PayStatus>(){
            @Override
            public PayStatus run(){

                return null;
            }
        }.execute();
    }

    /**
     * 发货
     * @param tradeRequest
     * @return
     */
    @Override
    public ResultDTO<DeliverStatus> deliver(TradeRequest tradeRequest){
        return new ResultExcutor<DeliverStatus>() {
            public DeliverStatus run(){
                return null;
            }
        }.execute();
    }

    /**
     * 收货
     * @param tradeRequest
     * @return
     */
    @Override
    public ResultDTO<Void> done(TradeRequest tradeRequest){
        return new ResultExcutor<Void>(){
            public Void run(){
                return null;
            }
        }.execute();
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
