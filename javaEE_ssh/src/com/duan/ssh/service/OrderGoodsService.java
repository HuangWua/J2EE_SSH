package com.duan.ssh.service;

import com.duan.ssh.entity.OrderGoods;

import java.util.List;

public interface OrderGoodsService {

    public int insertOrderGoods(OrderGoods orderGoods) throws Exception;

    public int updateOrderGoods(OrderGoods orderGoods) throws Exception;

    public int deleteOrderGoods(OrderGoods orderGoods) throws Exception;

    public List<OrderGoods> selectAllOrderGoods() throws Exception;

    public OrderGoods getOrderGoodsById(int id) throws Exception;

    public List<OrderGoods> selectOGByOrderId(int order_id) throws Exception;

    public int deleteOGByOrderId(int order_id) throws Exception;

}
