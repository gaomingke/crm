package com.kaishengit.mapper;

import com.kaishengit.pojo.User;

public interface UserMapper {

    /**
     * 根据手机号码查找用户
     * @param tel
     * @return
     */
    User findByTel(String tel);
}
