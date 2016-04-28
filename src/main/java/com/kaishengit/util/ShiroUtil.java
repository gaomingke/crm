package com.kaishengit.util;

import com.kaishengit.pojo.User;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    /**
     * 判断当前是否登录状态
     * @return
     */
    public static boolean isCurrentUser(){
        return getCurrentUser() != null;
    }

    /**
     * 查询当前登录者信息
     * @return ShiroUser
     */
    public static User getCurrentUser() {
        return  (User) SecurityUtils.getSubject().getPrincipal();
    }
    /**
     * 查询当前登录者 用户名
     * @return
     */
    public static String getCurrentUserName() {
        return getCurrentUser().getUsername();
    }
    /**
     * 查询当前登录者 用户Id
     * @return
     */
    public static Integer getCurrentUserId() {
        return getCurrentUser().getId();
    }

}
