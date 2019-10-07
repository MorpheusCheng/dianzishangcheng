package com.bdilab.demo.service;

import com.bdilab.demo.base.ResultDTO;
import com.bdilab.demo.request.ViewItemRequest;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
public interface ItemService {
    ResultDTO<Void> viewItem(ViewItemRequest viewItemRequest);
}
