package com.duan.ssh.service.impl;

import com.duan.ssh.dao.OrderGoodsDao;
import com.duan.ssh.entity.OrderGoods;
import com.duan.ssh.service.OrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.service.impl
 * @ClassName: OrderGoodsServiceImpl
 * @Author: duan
 * @Description: 订单商品信息服务
 * @Date: 2019-05-31 15:28
 * @Version: 1.0
 */
@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {

    @Autowired
    private OrderGoodsDao orderGoodsDao;

    public OrderGoodsDao getOrderGoodsDao() {
        return orderGoodsDao;
    }

    public void setOrderGoodsDao(OrderGoodsDao orderGoodsDao) {
        this.orderGoodsDao = orderGoodsDao;
    }

    @Override
    public int insertOrderGoods(OrderGoods orderGoods) throws Exception {
        int result = orderGoodsDao.insertOrderGoods(orderGoods);
        return result;
    }

    @Override
    public int updateOrderGoods(OrderGoods orderGoods) throws Exception {
        int result = orderGoodsDao.updateOrderGoods(orderGoods);
        return result;
    }

    @Override
    public int deleteOrderGoods(OrderGoods orderGoods) throws Exception {
        int result = orderGoodsDao.deleteOrderGoods(orderGoods);
        return result;
    }

    @Override
    public List<OrderGoods> selectAllOrderGoods() throws Exception {
        List<OrderGoods> ogList = orderGoodsDao.selectAllOrderGoods();
        return ogList;
    }

    @Override
    public OrderGoods getOrderGoodsById(int id) throws Exception {
        OrderGoods og = orderGoodsDao.getOrderGoodsById(id);
        return og;
    }

    @Override
    public List<OrderGoods> selectOGByOrderId(int order_id) throws Exception {

        List<OrderGoods> ogList = orderGoodsDao.selectOGByOrderId(order_id);

        return ogList;
    }

    @Override
    public int deleteOGByOrderId(int order_id) throws Exception {
        int result = orderGoodsDao.deleteOGByOrderId(order_id);
        return result;
    }
}
