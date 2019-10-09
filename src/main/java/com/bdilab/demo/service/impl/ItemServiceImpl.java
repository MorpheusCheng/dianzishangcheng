package com.bdilab.demo.service.impl;

import com.bdilab.demo.BizCode;
import com.bdilab.demo.base.ResultDTO;
import com.bdilab.demo.base.ResultExcutor;
import com.bdilab.demo.cache.BaseCache;
import com.bdilab.demo.cache.ItemCache;
import com.bdilab.demo.dao.ItemDAO;
import com.bdilab.demo.dto.BaseDTO;
import com.bdilab.demo.dto.ItemDTO;
import com.bdilab.demo.request.ViewItemRequest;
import com.bdilab.demo.service.ItemService;
import org.springframework.stereotype.Service;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public ResultDTO<Void> viewItem(ViewItemRequest viewItemRequest){
            return new ResultExcutor<Void>(){
                @Override
                public Void run(){
                    ItemCache itemCache = new ItemCache();
                    ItemDTO itemDTO = (ItemDTO) itemCache.get(viewItemRequest.getId());
                    if (itemDTO == null){
                        itemDTO = (ItemDTO) new ItemDAO().select(viewItemRequest.getId());
                    }
                    if (itemDTO == null){
                        System.out.println("数据库中无该item ：" + viewItemRequest.getId());
                        return null;
                    }
                    if (viewItemRequest.getBizCode() == BizCode.DEAULT){
                        System.out.println("默认模式 view item " + itemDTO.getId() + " name " + itemDTO.getName() + " price " + itemDTO.getPrice());
                    }else if (viewItemRequest.getBizCode() == BizCode.IRON_MAN){
                        if ("true".equals(itemDTO.getFeature("ENABLE"))){
                            System.out.println("钢铁侠模式 view item " + itemDTO.getId() + " name " + itemDTO.getName() + " price " + itemDTO.getPrice());
                        }else {
                            System.out.println("钢铁侠模式 禁止查看该item");
                        }
                    }else {
                        if ("true".equals(itemDTO.getFeature("ENABLE"))){
                            System.out.println("SPACEX模式 禁止查看该item");
                        }else {
                            System.out.println("SPACEX 模式 view item " + itemDTO.getId() + " name " + itemDTO.getName() + " price " + itemDTO.getPrice());
                        }
                    }
                    return null;
                }
            }.execute();
    }
}
