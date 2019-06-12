package com.duan.ssh.action;

import cn.hutool.core.io.FileUtil;
import com.duan.ssh.entity.Goods;
import com.duan.ssh.model.PageBean;
import com.duan.ssh.service.GoodsService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.action
 * @ClassName: GoodsAction
 * @Author: duan
 * @Description: 商品操作
 * @Date: 2019-05-31 14:41
 * @Version: 1.0
 */

@Controller
@Scope("prototype")
@ParentPackage("custom_default")
@Namespace("/goods")
@Results({
        @Result(name = "error", location = "/error.jsp"),
        @Result(name = "success", type = "json")
})
public class GoodsAction extends ActionSupport {

    @Autowired
    private GoodsService goodsService;
    private Goods goods;
    private List<Goods> goodsList = new ArrayList<>();
    private int result;
    private Integer priceLow;
    private Integer priceHigh;
    private File upload;
    private String uploadFileName;

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setPriceLow(Integer priceLow) {
        this.priceLow = priceLow;
    }

    public void setPriceHigh(Integer priceHigh) {
        this.priceHigh = priceHigh;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    //
    @Override
    public String execute() throws Exception {
        return super.execute();
    }


    //
    @Action(value = "selectGoodsById")
    public String selectGoodsById() throws Exception {
        goods = goodsService.getGoodsById(goods.getId());
        return "success";
    }


    //
    @Action(value = "selectAllGoods")
    public String selectAllGoods() throws Exception {
        goodsList = goodsService.selectAllGoods();
        return "success";
    }

    private String goods_name;

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }


    //
    @Action(value = "findGoodsByName")
    public String findGoodsByName() throws Exception {

        goodsList = goodsService.findGoodsByName(goods_name);

        return "success";

    }


    //
    @Action(value = "selectGoodsByType")
    public String selectGoodsByType() throws Exception {

        goodsList = goodsService.selectGoodsByType(goods.getType());

        return "success";
    }


    //
    @Action(value = "insertGoods", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("managerStack")
    })
    public String insertGoods() throws Exception {

        if (upload != null) {

            String pic_path = "F:\\idea\\IdeaProjects\\uploadFile";

            File file = new File(pic_path);

            String newFileName = UUID.randomUUID() + uploadFileName.substring(uploadFileName.lastIndexOf("."));

            FileUtil.copyFile(upload, new File(file, newFileName));

            goods.setImg(newFileName);

        }

        result = goodsService.insertGoods(goods);

        return result == 0 ? "error" : "success";
    }


    //
    @Action(value = "deleteGoods", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("managerStack")
    })
    public String deleteGoods() throws Exception {

        result = goodsService.deleteGoods(goods);

        return result == 0 ? "error" : "success";
    }


    //
    @Action(value = "updateGoods", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("managerStack")
    })
    public String updateGoods() throws Exception {

        if (upload != null) {

            String pic_path = "F:\\idea\\IdeaProjects\\uploadFile";

            File file = new File(pic_path);

            String newFileName = UUID.randomUUID() + uploadFileName.substring(uploadFileName.lastIndexOf("."));

            FileUtil.copyFile(upload, new File(file, newFileName));

            goods.setImg(newFileName);
        }

        result = goodsService.updateGoods(goods);

        return result == 0 ? "error" : "success";
    }


    //
    @Action(value = "priceBetween")
    public String selectGoodsPriceBetween() throws Exception {

        goodsList = goodsService.selectGoodsPriceBetween(priceLow, priceHigh);

        return "success";
    }


    //
    @Action(value = "goodsSellOut", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String goodsSellOut() throws Exception {

        result = 1;

        for (Goods good : goodsList) {
            if (goodsService.goodsSellOut(good) != 1) {
                result = goodsService.goodsSellOut(good);
                return "error";
            }
        }
        return "success";
    }

    private String check_result;

    public String getCheck_result() {
        return check_result;
    }

    public void setCheck_result(String check_result) {
        this.check_result = check_result;
    }


    //
    @Action(value = "checkGoodsCount", results = {
            @Result(name = "success", type = "json", params = {"root", "check_result"})
    })
    public String checkGoodsCount() throws Exception {

        Goods checkG = null;

        for (Goods good : goodsList) {
            checkG = goodsService.checkGoodsCount(good);
            if (checkG == null) {
                check_result = "success";
            } else {
                check_result = "商品：" + checkG.getName() + "（库存不足）!";
                return "success";
            }

        }

        return "success";
    }


    //
    private PageBean<Goods> pageBean = new PageBean<>();

    public PageBean<Goods> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<Goods> pageBean) {
        this.pageBean = pageBean;
    }


    //
    @Action(value = "selectgoodsByPage", results = {
            @Result(name = "success", type = "json", params = {"root", "pageBean"})
    })
    public String selectGoodsByPage() throws Exception {

        pageBean = goodsService.getOrderByPage(pageBean.getPageNum(), pageBean.getPageSize());

        return "success";
    }

}
