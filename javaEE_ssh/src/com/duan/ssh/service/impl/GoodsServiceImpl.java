package com.duan.ssh.service.impl;

import com.duan.ssh.dao.GoodsDao;
import com.duan.ssh.entity.Goods;
import com.duan.ssh.model.PageBean;
import com.duan.ssh.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.service.impl
 * @ClassName: GoodsServiceImpl
 * @Author: duan
 * @Description: 商品服务
 * @Date: 2019-05-31 15:23
 * @Version: 1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public GoodsDao getGoodsDao() {
        return goodsDao;
    }

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public int insertGoods(Goods goods) throws Exception {

        goods.setCreatetime(new Timestamp(new Date().getTime()));
        int result = goodsDao.insertGoods(goods);
        return result;
    }

    @Override
    public int updateGoods(Goods goods) throws Exception {

        goods.setCreatetime(new Timestamp(new Date().getTime()));

        int result = goodsDao.updateGoods(goods);
        return result;
    }

    @Override
    public int deleteGoods(Goods goods) throws Exception {

        int result = goodsDao.deleteGoods(goods);
        return result;
    }

    @Override
    public List<Goods> selectAllGoods() throws Exception {

        List<Goods> goodsList = goodsDao.selectAllGoods();
        return goodsList;
    }

    @Override
    public List<Goods> findGoodsByName(String goods_name) throws Exception {

        List<Goods> goodsList = goodsDao.findGoodsByName(goods_name);
        return goodsList;
    }

    @Override
    public List<Goods> selectGoodsByType(String type) throws Exception {

        List<Goods> goodsList = goodsDao.selectGoodsByType(type);

        return goodsList;
    }

    @Override
    public Goods getGoodsById(int id) throws Exception {

        Goods goods = goodsDao.getGoodsById(id);
        return goods;
    }

    @Override
    public List<Goods> selectGoodsPriceBetween(Integer low, Integer high) throws Exception {

        List<Goods> goodsList = goodsDao.selectGoodsPriceBetween(low, high);

        return goodsList;
    }

    @Override
    public Goods checkGoodsCount(Goods good) throws Exception {

        Goods goods = goodsDao.getGoodsById(good.getId());

        if (goods.getCount() < good.getCount()) {
            return goods;
        } else {
            return null;
        }

    }

    @Override
    public int goodsSellOut(Goods good) throws Exception {

        Goods sellG = null;
        int result = 0;

        try {
            sellG = goodsDao.getGoodsById(good.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        if (sellG.getCount() < good.getCount()) {
            result = 2;
        } else {
            sellG.setCount(sellG.getCount() - good.getCount());
            result = goodsDao.updateGoods(sellG);
        }

        return result;

    }

    @Override
    public PageBean<Goods> getOrderByPage(int pageNum, int pageSize) throws Exception {

        int totalRecord = goodsDao.goodsCount();

        PageBean pb = new PageBean(pageNum, pageSize, totalRecord);

        pb.setList(goodsDao.pageGoodsList(pb.getStartIndex(), pageSize));

        return pb;
    }

}
