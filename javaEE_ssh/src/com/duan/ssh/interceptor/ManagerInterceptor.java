package com.duan.ssh.interceptor;

import com.duan.ssh.action.*;
import com.duan.ssh.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.interceptor
 * @ClassName: ManagerInterceptor
 * @Author: duan
 * @Description: 后台管理拦截
 * @Date: 2019-06-06 14:56
 * @Version: 1.0
 */

public class ManagerInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        System.out.println("ManagerInterceptor......");

        Object obj = actionInvocation.getAction();

        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");

        if (user.getRole() != 2) {

            if (obj != null) {
                if (obj instanceof UserAction) {

                    UserAction action = (UserAction) obj;
                    action.setResult(105);

                } else if (obj instanceof OrderGoodsAction) {

                    OrderGoodsAction action = (OrderGoodsAction) obj;
                    action.setResult(105);

                } else if (obj instanceof OrdersAction) {

                    OrdersAction action = (OrdersAction) obj;
                    action.setResult(105);

                } else if (obj instanceof ShoppingCartAction) {

                    ShoppingCartAction action = (ShoppingCartAction) obj;
                    action.setResult(105);

                } else if (obj instanceof GoodsAction) {

                    GoodsAction action = (GoodsAction) obj;
                    action.setResult(105);

                }
            }
            return "power_low";
        } else {
            return actionInvocation.invoke();
        }

    }
}
