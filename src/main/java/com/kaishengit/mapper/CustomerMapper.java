package com.kaishengit.mapper;

import com.kaishengit.pojo.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {

    /**
     * 添加新客户
     * @param customer
     */
    void save(Customer customer);

    /**
     * 根据DataTables的查询方式获取 客户列表
     * @param param
     * @return
     */
    List<Customer> findByParam(Map<String, Object> param);

    /**
     * 获取客户的总数量
     * @return
     */
    Long count();

    /**
     * 根据DataTables的查询方式获取客户总数量
     * @param param
     * @return
     */
    Long countByParam(Map<String, Object> param);
}
