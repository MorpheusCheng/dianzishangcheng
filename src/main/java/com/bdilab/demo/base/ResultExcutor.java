package com.bdilab.demo.base;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
public abstract class ResultExcutor<T> {
    public abstract T run();

    public ResultDTO<T> execute(){

        ResultDTO<T> resultDTO = ResultDTO.of(true);

        try {
            T data = run();
            resultDTO.setData(data);
        }catch (Throwable e){
            resultDTO.setSuccess(false);
        }

        return resultDTO;
    }
}
