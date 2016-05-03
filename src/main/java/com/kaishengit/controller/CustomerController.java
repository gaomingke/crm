package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.pojo.Customer;
import com.kaishengit.service.CustomerService;
import com.kaishengit.util.Strings;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Inject
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "customer/list";
    }


    /**
     * DataTables加载数据
     * @return
     */
    @RequestMapping(value = "/customers.json",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request) {
        Map<String,Object> resultMap = Maps.newHashMap();

        String draw = request.getParameter("draw");
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length = Integer.valueOf(request.getParameter("length"));
        String searchName = request.getParameter("seaName");
        String searchTel = request.getParameter("seaTel");
        String searchState = request.getParameter("seaState");
        String orderColumnIndex = request.getParameter("order[0][column]");
        String orderType = request.getParameter("order[0][dir]");
        String orderColumnName = request.getParameter("columns["+orderColumnIndex+"][name]");

        Map<String,Object> param = Maps.newHashMap();
        param.put("start",start);
        param.put("length",length);
        if(StringUtils.isNotEmpty(searchName)) {
            param.put("seaName", "%" + Strings.toUTF8(searchName) + "%");
        }
        if(StringUtils.isNotEmpty(searchTel)) {
            param.put("seaTel", "%" + Strings.toUTF8(searchTel) + "%");
        }
        if(StringUtils.isNotEmpty(searchState)) {
            param.put("seaState",Strings.toUTF8(searchState));
        }
        if(orderColumnName == null || orderType == null) {
            param.put("orderColumn","id");
            param.put("orderType","desc");
        } else {
            param.put("orderColumn", orderColumnName);
            param.put("orderType", orderType);
        }

        List<Customer> userList = customerService.findUserByParam(param); //.findAllUser();
        Integer count = customerService.findCustomerCount();
        Integer filteredCount = customerService.findUserCountByParam(param);

        resultMap.put("draw",draw);
        resultMap.put("recordsTotal",count); //总记录数
        resultMap.put("recordsFiltered",filteredCount); //过滤出来的数量
        resultMap.put("data",userList);

        return resultMap;
    }


    /**
     * 添加新客户
     * @return
     */
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    @ResponseBody
    public String newCust(Customer customer) {
        customerService.saveNewCustomer(customer);
        return "success";
    }






}
