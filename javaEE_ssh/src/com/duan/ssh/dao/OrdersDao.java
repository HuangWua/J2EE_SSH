package com.duan.ssh.dao;

import com.duan.ssh.entity.Order;
import com.duan.ssh.entity.OrderGoods;

import java.util.List;
import java.util.Set;

public interface OrdersDao {

    public int insertOrders(Order order) throws Exception;

    public int updateOrders(Order order) throws Exception;

    public int deleteOrders(Order order) throws Exception;

    public List<Order> selectAllOrders() throws Exception;

    public Order getOrdersById(int id) throws Exception;

    public Set<OrderGoods> selectOG(int id) throws Exception;

//    public List<Order> getOrdersByUname(String username) throws Exception;

    public List<Order> getOrdersByPrice(Integer low, Integer height) throws Exception;

    public int orderCount() throws Exception;

    public List<Order> pageOrderList(int startIndex, int pageSize) throws Exception;

}
