package com.study.demo.taotao_dao.mapper;

import com.study.demo.taotao_pojo.pojo.TbUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 插入用户注册信息
     * @param tbUser 用户信息
     * @return 插入成功返回1
     */
    int insertTbUser(TbUser tbUser);
}
