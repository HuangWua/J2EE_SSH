package com.duan.ssh.dao;

import com.duan.ssh.entity.Goods;

import java.util.List;

public interface GoodsDao {

    public int insertGoods(Goods goods) throws Exception;

    public int updateGoods(Goods goods) throws Exception;

    public int deleteGoods(Goods goods) throws Exception;

    public List<Goods> selectAllGoods() throws Exception;

    public List<Goods> findGoodsByName(String goods_name) throws Exception;

    public List<Goods> selectGoodsByType(String type) throws Exception;

    public Goods getGoodsById(int id) throws Exception;

    public List<Goods> selectGoodsPriceBetween(Integer low, Integer high) throws Exception;

    public int goodsCount() throws Exception;

    public List<Goods> pageGoodsList(int startIndex, int pageSize) throws Exception;
}
