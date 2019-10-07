package com.bdilab.demo.base;

import lombok.Data;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
@Data
public class ResultDTO<T> {
    private boolean success;
    private T data;
    private String errorCode;

    public static  <T> ResultDTO of(T data){
        ResultDTO<T> resultDTO = ResultDTO.of(true);
        resultDTO.setData(data);
        return resultDTO;
    }

    public static <T> ResultDTO of(boolean isSuccess){
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(isSuccess);
        return resultDTO;
    }

}
