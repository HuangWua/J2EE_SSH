package com.duan.ssh.action;

import com.duan.ssh.entity.Shoppingcart;
import com.duan.ssh.service.ShoppingCartService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.action
 * @ClassName: ShoppingCartAction
 * @Author: duan
 * @Description: 购物车操作
 * @Date: 2019-05-31 15:37
 * @Version: 1.0
 */
@Controller
@Scope("prototype")
@ParentPackage("custom_default")
@Namespace("/shoppingCart")
@Results({
        @Result(name = "error", location = "/error.jsp"),
        @Result(name = "success", type = "json")
})
public class ShoppingCartAction extends ActionSupport {

    @Autowired
    private ShoppingCartService shoppingCartService;
    private Shoppingcart sc;
    private List<Shoppingcart> scList;
    private int result;


    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<Shoppingcart> getScList() {
        return scList;
    }

    public void setScList(List<Shoppingcart> scList) {
        this.scList = scList;
    }

    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public Shoppingcart getSc() {
        return sc;
    }

    public void setSc(Shoppingcart sc) {
        this.sc = sc;
    }


    //
    @Action(value = "selectShoppingcartById", interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String selectShoppingcartById() throws Exception {

        sc = shoppingCartService.getShoppingcartById(sc.getId());

        return "success";
    }


    //
    @Action(value = "selectAllShoppingcart", interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String selectAllShoppingcart() throws Exception {

        scList = shoppingCartService.selectAllShoppingcart();

        return "success";
    }


    //
    @Action(value = "insertShoppingcart", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String insertShoppingcart() throws Exception {

        result = shoppingCartService.insertShoppingcart(sc);

        return result == 0 ? "error" : "success";
    }


    //
    @Action(value = "deleteShoppingcart", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String deleteShoppingcart() throws Exception {

        result = shoppingCartService.deleteShoppingcart(sc);

        return result == 0 ? "error" : "success";
    }


    //
    @Action(value = "updateShoppingcart", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String updateShoppingcart() throws Exception {

        result = shoppingCartService.updateShoppingcart(sc);

        return result == 0 ? "error" : "success";
    }

    private List<Integer> scDel;

    public List<Integer> getScDel() {
        return scDel;
    }

    public void setScDel(List<Integer> scDel) {
        this.scDel = scDel;
    }


    //批量删除购物车记录
    @Action(value = "deleteSomeSc", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    })
    public String deleteSomeSc() throws Exception {

        sc = new Shoppingcart();
        for (Integer i : scDel) {
            sc.setId(i);
            result = shoppingCartService.deleteShoppingcart(sc);
        }

        return "success";
    }

}
