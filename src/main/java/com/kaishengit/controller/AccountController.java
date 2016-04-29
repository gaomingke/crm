package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
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
@RequestMapping("/account")
public class AccountController {

    @Inject
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "account/list";
    }

    /**
     * 让DataTables控件加载数据
     * @return
     */
    @RequestMapping(value = "/users.json",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request) {

        String draw = request.getParameter("draw");
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length = Integer.valueOf(request.getParameter("length"));
        String searchValue = request.getParameter("search[value]");
        String orderColumnIndex = request.getParameter("order[0][column]");
        String orderType = request.getParameter("order[0][dir]");
        String orderColumnName = request.getParameter("columns["+orderColumnIndex+"][name]");

        Map<String,Object> param = Maps.newHashMap();
        param.put("start",start);
        param.put("length",length);
        if(StringUtils.isNotEmpty(searchValue)) {
            param.put("keyword", "%" + searchValue + "%");
        }
        param.put("orderColumn",orderColumnName);
        param.put("orderType",orderType);



        Map<String,Object> result = Maps.newHashMap();

        List<User> userList = userService.findUserByParam(param); //.findAllUser();

        result.put("draw",draw);
        result.put("recordsTotal",userList.size());
        result.put("recordsFiltered",userList.size());
        result.put("data",userList);


        return result;
    }
}
