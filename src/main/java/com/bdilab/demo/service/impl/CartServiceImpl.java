package com.bdilab.demo.service.impl;

import com.bdilab.demo.base.ResultDTO;
import com.bdilab.demo.base.ResultExcutor;
import com.bdilab.demo.dao.CartDAO;
import com.bdilab.demo.dto.CartDTO;
import com.bdilab.demo.request.AddCartRequest;
import com.bdilab.demo.service.CartService;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
@Service
public class CartServiceImpl implements CartService {
    @Override
    public ResultDTO<Void> addCart(AddCartRequest addCartRequest){
        return new ResultExcutor<Void>(){
            @Override
            public Void run(){
                System.out.println("cart service cardId : " + addCartRequest.getCartId());
                CartDAO cartDAO = new CartDAO();
                CartDTO cartDTO = (CartDTO) cartDAO.select(addCartRequest.getCartId());
                if (cartDTO == null){
                    cartDTO = new CartDTO();
                    cartDTO.setId(addCartRequest.getCartId());
                    List<Pair<Long,Integer>> list = new ArrayList<>();
                    cartDTO.setItems(list);
                    System.out.println("首次添加该购物车");
                }
                List<Pair<Long,Integer>> items = cartDTO.getItems();
                items.add(new Pair<>(addCartRequest.getItemId(),addCartRequest.getCount()));
                cartDTO.setItems(items);
                cartDAO.insertOrUpdate(cartDTO);
                System.out.println("更新购物车 : " + cartDTO.getId());
                return null;
            }
        }.execute();
    }
}
