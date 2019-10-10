package com.bdilab.demo.controller;

import com.bdilab.demo.BizCode;
import com.bdilab.demo.base.ResultDTO;
import com.bdilab.demo.dao.CartDAO;
import com.bdilab.demo.dao.ItemDAO;
import com.bdilab.demo.dto.CartDTO;
import com.bdilab.demo.dto.ItemDTO;
import com.bdilab.demo.dto.TradeDTO;
import com.bdilab.demo.request.AddCartRequest;
import com.bdilab.demo.request.TradeRequest;
import com.bdilab.demo.request.ViewItemRequest;
import com.bdilab.demo.service.CartService;
import com.bdilab.demo.service.impl.ItemServiceImpl;
import com.bdilab.demo.service.impl.TradeServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
@RestController
public class Controller {
    @Resource
    private CartService cartService;

    @PostMapping(value = "/addCart")
    public String addCart(
            @RequestParam(name = "id")
            long id,
            @RequestParam(name = "itemid")
            long itemId,
            @RequestParam(name = "count")
            int count
    ){
        AddCartRequest addCartRequest = new AddCartRequest();
        addCartRequest.setCartId(id);
        addCartRequest.setItemId(itemId);
        addCartRequest.setCount(count);
        ResultDTO resultDTO = cartService.addCart(addCartRequest);

        return "hello" + resultDTO.isSuccess();
    }
    @PostMapping(value = "/test")
    public String test(){
        ItemDAO itemDAO = new ItemDAO();
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(1234l);
        itemDTO.setName("测试商品");
        itemDTO.setPrice(1000);
        itemDAO.insertOrUpdate(itemDTO);
        return "ok 插入商品 id:" + itemDTO.getId();
    }

    @GetMapping(value = "/viewitem/{id}")
    public String viewItem(
            @PathVariable(name = "id")
            Long id
    ){
        ViewItemRequest itemRequest = new ViewItemRequest();
        itemRequest.setBizCode(BizCode.DEAULT);
        itemRequest.setId(id);
        ItemServiceImpl itemService = new ItemServiceImpl();
        ResultDTO resultDTO = itemService.viewItem(itemRequest);
        return "ok" + resultDTO.isSuccess();
    }

    @PostMapping(value = "/getTrade")
    public String getTrade(
            @RequestParam(name = "cartid")
            long cartId,
            @RequestParam(name = "tradeid")
            long tradeId
    ){
        TradeRequest tradeRequest = new TradeRequest();
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cartId);
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setId(tradeId);
        tradeRequest.setCartDTO(cartDTO);
        tradeRequest.setTradeDTO(tradeDTO);
        tradeRequest.setBizCode(BizCode.DEAULT);

        TradeServiceImpl tradeService = new TradeServiceImpl();
        ResultDTO resultDTO = tradeService.get(tradeRequest);

        return "ok " + resultDTO.isSuccess();
    }

    @PostMapping(value = "/createtrade")
    public String createTrade(
            @RequestParam(value = "tradeid")
            long tradeId,
            @RequestParam(value = "cartid")
            long cartid
    ){
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setBizCode(BizCode.DEAULT);
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setId(tradeId);
        tradeRequest.setTradeDTO(tradeDTO);

        TradeServiceImpl tradeService = new TradeServiceImpl();
        ResultDTO resultDTO = tradeService.createTrade(tradeRequest);

        return "ok " + resultDTO.isSuccess();
    }

    @PostMapping(value = "/pay")
    public String pay(
            @RequestParam(value = "tradeid")
            long tradeId
    ){
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setBizCode(BizCode.DEAULT);
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setId(tradeId);

        tradeRequest.setTradeDTO(tradeDTO);

        TradeServiceImpl tradeService = new TradeServiceImpl();
        ResultDTO resultDTO = tradeService.pay(tradeRequest);

        return "ok " + resultDTO.isSuccess();
    }

    @PostMapping(value = "/deliver")
    public String deliver(
            @RequestParam(value = "tradeid")
            long tradeId
    ){
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setBizCode(BizCode.DEAULT);
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setId(tradeId);

        tradeRequest.setTradeDTO(tradeDTO);

        TradeServiceImpl tradeService = new TradeServiceImpl();
        ResultDTO resultDTO = tradeService.deliver(tradeRequest);

        return "ok " + resultDTO.isSuccess();
    }

    @PostMapping(value = "/done")
    public String done(
            @RequestParam(value = "tradeid")
                    long tradeId
    ){
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setBizCode(BizCode.DEAULT);
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setId(tradeId);

        tradeRequest.setTradeDTO(tradeDTO);

        TradeServiceImpl tradeService = new TradeServiceImpl();
        ResultDTO resultDTO = tradeService.done(tradeRequest);

        return "ok " + resultDTO.isSuccess();
    }



}
