package com.bdilab.demo.dao;

import com.bdilab.demo.dto.BaseDTO;


/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
public abstract class BaseDAO {
    public abstract String getIdString(Long id);

    public void insertOrUpdate(BaseDTO baseDTO){
        String id = getIdString(baseDTO.getId());
        DB.db.put(id,baseDTO);
    }

    public BaseDTO select(Long lId){
        String id = getIdString(lId);
        return (BaseDTO) DB.db.get(id);
    }

    public int delete(Long lId){
        String id = getIdString(lId);

        if (!DB.db.containsKey(id)){
            return 0;
        }

        DB.db.remove(id);
        return 1;
    }
}
