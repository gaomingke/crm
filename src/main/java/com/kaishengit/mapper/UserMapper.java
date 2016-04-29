package com.kaishengit.mapper;

import com.kaishengit.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 根据手机号码查找用户
     * @param tel
     * @return
     */
    User findByTel(String tel);

    /**
     * 获取所有的用户
     * @return
     */
    List<User> findAll();

    /**
     * 根据DataTables中的参数进行查询
     * @param param
     * @return
     */
    List<User> findByParam(Map<String, Object> param);
}
