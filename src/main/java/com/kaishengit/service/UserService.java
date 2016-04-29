package com.kaishengit.service;

import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
@Transactional
public class UserService {

    @Inject
    private UserMapper userMapper;

    public List<User> findAllUser() {
        return userMapper.findAll();
    }

    /**
     * 根据DataTables中的参数进行查询
     * @param param
     * @return
     */
    public List<User> findUserByParam(Map<String, Object> param) {
        return userMapper.findByParam(param);
    }
}
