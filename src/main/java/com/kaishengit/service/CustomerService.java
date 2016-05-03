package com.kaishengit.service;

import com.kaishengit.mapper.CustomerMapper;
import com.kaishengit.pojo.Customer;
import com.kaishengit.util.ShiroUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
@Transactional
public class CustomerService {

    private Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Inject
    private CustomerMapper customerMapper;

    /**
     * 保存新客户
     * @param customer
     */
    public void saveNewCustomer(Customer customer) {
        customer.setProgress(Customer.PROGRESS_NEW);
        customer.setUserid(ShiroUtil.getCurrentUserId());
        customer.setCreatetime(DateTime.now().toString("yyyy-MM-dd HH:mm"));

        customerMapper.save(customer);

        logger.info("{}-添加了新客户-{}",ShiroUtil.getCurrentUserName(),customer.getCustname());
    }

    /**
     * 根据DataTables的查询方式获取 客户列表
     * @param param
     * @return
     */
    public List<Customer> findUserByParam(Map<String, Object> param) {
        return customerMapper.findByParam(param);
    }

    /**
     * 获取客户的总数量
     * @return
     */
    public Integer findCustomerCount() {
        return customerMapper.count().intValue();
    }

    /**
     * 根据DataTables的查询方式获取客户总数量
     * @param param
     * @return
     */
    public Integer findUserCountByParam(Map<String, Object> param) {
        return customerMapper.countByParam(param).intValue();
    }
}
