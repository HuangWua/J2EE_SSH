package com.duan.ssh.action;

import com.duan.ssh.entity.OrderGoods;
import com.duan.ssh.service.OrderGoodsService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.action
 * @ClassName: OrderGoodsAction
 * @Author: duan
 * @Description: 订单商品操作
 * @Date: 2019-05-31 15:38
 * @Version: 1.0
 */
@Controller
@Scope("prototype")
@ParentPackage("custom_default")
@Namespace("/order_goods")
@Results({
        @Result(name = "error", location = "/error.jsp"),
        @Result(name = "success", type = "json")
})
public class OrderGoodsAction extends ActionSupport {

    @Autowired
    private OrderGoodsService orderGoodsService;
    private OrderGoods orderGoods;
    private List<OrderGoods> ogList;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    private int result;

    public List<OrderGoods> getOgList() {
        return ogList;
    }

    public void setOgList(List<OrderGoods> ogList) {
        this.ogList = ogList;
    }

    public void setOrderGoodsService(OrderGoodsService orderGoodsService) {
        this.orderGoodsService = orderGoodsService;
    }

    public OrderGoods getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(OrderGoods orderGoods) {
        this.orderGoods = orderGoods;
    }


    //
    @Action(value = "getOrderGoodsById", interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String getOrderGoodsById() throws Exception {

        orderGoods = orderGoodsService.getOrderGoodsById(orderGoods.getId());

        return "seccess";
    }


    //
    @Action(value = "selectAllOrderGoods", interceptorRefs = {
            @InterceptorRef("managerStack")
    })
    public String selectAllOrderGoods() throws Exception {

        ogList = orderGoodsService.selectAllOrderGoods();

        return "success";
    }


    //
    @Action(value = "insertOrderGoods", interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String insertOrderGoods() throws Exception {

        int result = orderGoodsService.insertOrderGoods(orderGoods);

        return result == 0 ? "error" : "success";
    }


    //
    @Action(value = "deleteOrderGoods", interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String deleteOrderGoods() throws Exception {

        int result = orderGoodsService.deleteOrderGoods(orderGoods);

        return result == 0 ? "error" : "success";
    }


    //
    @Action(value = "updateOrderGoods", interceptorRefs = {
            @InterceptorRef("managerStack")
    })
    public String updateOrderGoods() throws Exception {

        int result = orderGoodsService.updateOrderGoods(orderGoods);

        return result == 0 ? "error" : "success";
    }


    //
    @Action(value = "insertManyOG", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String insertManyOG() throws Exception {

        for (OrderGoods orderGoods : ogList) {
            result = orderGoodsService.insertOrderGoods(orderGoods);
        }

        return "success";
    }


    //
    @Action(value = "selectOGByOrderId", interceptorRefs = {
            @InterceptorRef("loginStack")
    }, results = {
            @Result(name = "success", type = "json", params = {"root", "ogList"})
    })
    public String selectOGByOrderId() throws Exception {

        ogList = orderGoodsService.selectOGByOrderId(orderGoods.getOrder().getId());

        return "success";
    }


    @Action(value = "deleteOGByOrderId", interceptorRefs = {
            @InterceptorRef("loginStack")
    }, results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    })
    public String deleteOGByOrderId() throws Exception {

        result = orderGoodsService.deleteOGByOrderId(orderGoods.getOrder().getId());

        return "success";
    }

}
