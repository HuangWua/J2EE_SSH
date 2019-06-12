package com.duan.ssh.service;

import com.duan.ssh.entity.Order;
import com.duan.ssh.entity.OrderGoods;
import com.duan.ssh.model.PageBean;

import java.util.List;
import java.util.Set;

public interface OrdersService {

    public int insertOrders(Order order) throws Exception;

    public int updateOrders(Order order) throws Exception;

    public int deleteOrders(Order order) throws Exception;

    public List<Order> selectAllOrders() throws Exception;

    public Order getOrdersById(int id) throws Exception;

    public Set<OrderGoods> selectOG(int id) throws Exception;

//    public List<Order> getOrdersByUname(String username) throws Exception;

    public List<Order> getOrdersByPrice(Integer low, Integer high) throws Exception;

    public PageBean<Order> getOrderByPage(int pageNum,int pageSize) throws Exception;

}
