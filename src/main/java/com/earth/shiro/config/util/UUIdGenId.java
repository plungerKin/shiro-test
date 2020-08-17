package com.earth.shiro.config.util;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

/**
 * @author Jolian
 * @Date 2019-05-28 21:08
 **/
public class UUIdGenId implements GenId<String> {
    @Override
    public String genId(String table, String column) {
        return UUID.randomUUID().toString().replace("-","");
    }


    public static String getId(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
