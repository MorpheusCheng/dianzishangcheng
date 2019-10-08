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
        if (viewItemRequest.getBizCode() == BizCode.DEAULT){
            return new ResultExcutor<Void>(){
                @Override
                public Void run(){
                    System.out.println("view item");
                    BaseCache itemCache = new ItemCache();
                    ItemDTO itemDTO = (ItemDTO) itemCache.get(viewItemRequest.getId());
                    if (itemDTO == null){
                        itemDTO = (ItemDTO) new ItemDAO().select(viewItemRequest.getId());
                        itemCache.put(itemDTO);
                    }
                    System.out.println("view item " + itemDTO.getId()+" name: " + itemDTO.getName() + " price " + itemDTO.getPrice());
                    return null;
                }
            }.execute();
        }else if (viewItemRequest.getBizCode() == BizCode.IRON_MAN){

        }else {

        }
        return null;
    }
}
