package com.duan.ssh.service;

import com.duan.ssh.entity.Goods;
import com.duan.ssh.model.PageBean;

import java.util.List;

public interface GoodsService {

    public int insertGoods(Goods goods) throws Exception;

    public int updateGoods(Goods goods) throws Exception;

    public int deleteGoods(Goods goods) throws Exception;

    public List<Goods> selectAllGoods() throws Exception;

    public List<Goods> findGoodsByName(String goods_name) throws Exception;

    public List<Goods> selectGoodsByType(String type) throws Exception;

    public Goods getGoodsById(int id) throws Exception;

    public List<Goods> selectGoodsPriceBetween(Integer low, Integer high) throws Exception;

    public Goods checkGoodsCount(Goods good) throws Exception;

    public int goodsSellOut(Goods good) throws Exception;

    public PageBean<Goods> getOrderByPage(int pageNum, int pageSize) throws Exception;

}
