package com.eruekaclient.demo.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by  Hmq
 * @date on 2018/8/30.
 */
public interface HelloDao {

    String hello(@Param("param")String param);

    int update(@Param("loginname")String loginname);


}
