package com.bdilab.demo.cache;

import com.bdilab.demo.dto.BaseDTO;

/**
 * Author: cyz
 * Date: 2019/10/7
 * Description:
 */
public abstract class BaseCache {
    public abstract String getIdString(Long id);

    public void put(BaseDTO baseDTO){
        String id = getIdString(baseDTO.getId());
        Cache.cache.put(id,baseDTO);
    }

    public BaseDTO get(Long lId){
        String id = getIdString(lId);
        return (BaseDTO) Cache.cache.get(id);
    }

    public int remove(Long lId){
        String id = getIdString(lId);

        if (!Cache.cache.containsKey(id)){
            return 0;
        }

        Cache.cache.remove(id);
        return 1;
    }
}
