package com.bdilab.demo.request;

import com.bdilab.demo.dto.CartDTO;
import com.bdilab.demo.dto.TradeDTO;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
public class TradeRequest extends BaseRequest {
    /**
     * 购物车
     */
    private CartDTO cartDTO;
    /**
     * 交易单
     */
    private TradeDTO tradeDTO;

    public CartDTO getCartDTO() {
        return cartDTO;
    }

    public void setCartDTO(CartDTO cartDTO) {
        this.cartDTO = cartDTO;
    }

    public TradeDTO getTradeDTO() {
        return tradeDTO;
    }

    public void setTradeDTO(TradeDTO tradeDTO) {
        this.tradeDTO = tradeDTO;
    }
}
