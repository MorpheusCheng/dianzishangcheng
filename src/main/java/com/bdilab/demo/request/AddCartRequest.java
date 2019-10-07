package com.bdilab.demo.request;

import lombok.Data;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
@Data
public class AddCartRequest extends BaseRequest{
    Long cartId;

    Long itemId;

    Integer count;

}
