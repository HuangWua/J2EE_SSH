package com.duan.ssh.action;

import com.duan.ssh.entity.Order;
import com.duan.ssh.entity.OrderGoods;
import com.duan.ssh.model.PageBean;
import com.duan.ssh.service.OrdersService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.action
 * @ClassName: OrdersAction
 * @Author: duan
 * @Description: 订单操作
 * @Date: 2019-05-31 15:39
 * @Version: 1.0
 */

@Controller
@Scope("prototype")
@ParentPackage("custom_default")
@Namespace("/orders")
@Results({
        @Result(name = "error", location = "/error.jsp"),
        @Result(name = "success", type = "json")
})
public class OrdersAction extends ActionSupport {

    @Autowired
    private OrdersService ordersService;
    private Order order;
    private List<Order> orderList;
    //订单详情
    private Set<OrderGoods> setOG = new HashSet<>();

    //操作结果
    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Set<OrderGoods> getSetOG() {
        return setOG;
    }

    public void setSetOG(Set<OrderGoods> setOG) {
        this.setOG = setOG;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void setOrdersService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //
    @Action(value = "selectOrdersById", interceptorRefs = {
            @InterceptorRef("loginStack")
    }, results = {
            @Result(name = "success", type = "json", params = {"root", "order"})
    })
    public String selectOrdersById() throws Exception {

        order = ordersService.getOrdersById(id);

        return "success";
    }


    //
    @Action(value = "selectAllOrders", interceptorRefs = {
            @InterceptorRef("managerStack")
    }, results = {
            @Result(name = "success", type = "json", params = {"root", "orderList"})
    })
    public String selectAllOrders() throws Exception {

        orderList = ordersService.selectAllOrders();

        return "success";
    }


    //
    @Action(value = "insertOrders", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String insertOrders() throws Exception {

        result = ordersService.insertOrders(order);

        return result == 0 ? "error" : "success";
    }


    //
    @Action(value = "deleteOrders", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("managerStack")
    })
    public String deleteOrders() throws Exception {


        result = ordersService.deleteOrders(order);

        return result == 0 ? "error" : "success";
    }


    //
    @Action(value = "updateOrders", interceptorRefs = {
            @InterceptorRef("loginStack")
    }, results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    })
    public String updateOrders() throws Exception {

        result = ordersService.updateOrders(order);

        return result == 0 ? "error" : "success";
    }


    //
    @Action(value = "selectOG", results = {
            @Result(name = "OG", type = "json", params = {"root", "setOG"})
    }, interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String selectOG() throws Exception {

        setOG = ordersService.selectOG(id);

        return "OG";
    }


//    @Action(value = "selectOrdersByUname", interceptorRefs = {
//            @InterceptorRef("managerStack")
//    }, results = {
//            @Result(name = "success", type = "json", params = {"root", "orderList"})
//    })
//    public String selectOrdersByUname() throws Exception {
//
//        orderList = ordersService.getOrdersByUname(order.getUser().getUsername());
//
//        return "success";
//    }


    private Integer low;
    private Integer high;

    public void setLow(Integer low) {
        this.low = low;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }


    //
    @Action(value = "selectOrdersByPrice", interceptorRefs = {
            @InterceptorRef("managerStack")
    }, results = {
            @Result(name = "success", type = "json", params = {"root", "orderList"})
    })
    public String selectOrdersByPrice() throws Exception {

        orderList = ordersService.getOrdersByPrice(low, high);

        return "success";
    }


    private PageBean<Order> pageBean = new PageBean<>();

    public PageBean<Order> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<Order> pageBean) {
        this.pageBean = pageBean;
    }

    //
    @Action(value = "selectordersByPage", results = {
            @Result(name = "success", type = "json", params = {"root", "pageBean"})
    })
    public String selectOrderByPage() throws Exception {

        pageBean = ordersService.getOrderByPage(pageBean.getPageNum(), pageBean.getPageSize());

        return "success";
    }


}
