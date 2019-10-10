package com.bdilab.demo.service.impl;

import com.bdilab.demo.BizCode;
import com.bdilab.demo.base.ResultDTO;
import com.bdilab.demo.base.ResultExcutor;
import com.bdilab.demo.dao.CartDAO;
import com.bdilab.demo.dao.ItemDAO;
import com.bdilab.demo.dao.TradeDAO;
import com.bdilab.demo.dto.*;
import com.bdilab.demo.proxy.IronManCompanyProxy;
import com.bdilab.demo.proxy.SpaceXCompanyProxy;
import com.bdilab.demo.proxy.WePayCompanyProxy;
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

                tradeDTO = (TradeDTO) tradeDAO.select(tradeDTO.getId());

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
                    tradeDTO.setPayStatus(PayStatus.CREATED);
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
                    tradeDTO.setPayStatus(PayStatus.CREATED);
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
                    tradeDTO.setPayStatus(PayStatus.CREATED);
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
                TradeDTO tradeDTO = tradeRequest.getTradeDTO();
                TradeDAO tradeDAO = new TradeDAO();
                tradeDTO = (TradeDTO) tradeDAO.select(tradeDTO.getId());

                if (tradeDTO == null || tradeDTO.getPayStatus() == null){
                    tradeDTO = new TradeDTO();
                    tradeDTO.setPayStatus(PayStatus.PAY_FAIL);
                    System.out.println("支付失败");
                }else if (tradeRequest.getBizCode() == BizCode.IRON_MAN){
                    IronManCompanyProxy ironManCompanyProxy = new IronManCompanyProxy();
                    ironManCompanyProxy.pay();
                    tradeDTO.setPayStatus(PayStatus.PAY_SUCCESS);
                }else if (tradeRequest.getBizCode() == BizCode.SPACEX){
                    SpaceXCompanyProxy spaceXCompanyProxy = new SpaceXCompanyProxy();
                    try{
                        spaceXCompanyProxy.pay();
                    }catch (Exception e){
                        WePayCompanyProxy wePayCompanyProxy = new WePayCompanyProxy();
                        wePayCompanyProxy.pay();
                    }
                    tradeDTO.setPayStatus(PayStatus.PAY_SUCCESS);
                }
                tradeDAO.insertOrUpdate(tradeDTO);
                return tradeDTO.getPayStatus();
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
                TradeDTO tradeDTO = tradeRequest.getTradeDTO();
                TradeDAO tradeDAO = new TradeDAO();
                tradeDTO = (TradeDTO) tradeDAO.select(tradeDTO.getId());

                tradeDTO.setDeliverStatus(DeliverStatus.DELIVERING);
                tradeDTO.setTradeStatus(TradeStatus.DELIVERING);
                System.out.println("快递中");
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
                TradeDTO tradeDTO = tradeRequest.getTradeDTO();
                TradeDAO tradeDAO = new TradeDAO();
                tradeDTO = (TradeDTO) tradeDAO.select(tradeDTO.getId());

                tradeDTO.setDeliverStatus(DeliverStatus.DELIVER_SUCCESS);
                tradeDTO.setTradeStatus(TradeStatus.DONE);
                System.out.println("完结");
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
                CartDAO cartDAO = new CartDAO();
                CartDTO cartDTO = (CartDTO) cartDAO.select(tradeRequest.getTradeDTO().getId());

                tradeDTO.setItems(cartDTO.getItems());
                tradeDAO.insertOrUpdate(tradeDTO);
                System.out.println("加购交易: id " + tradeDTO.getId());
                return tradeDTO;
            }
        }.execute();
    }
}
