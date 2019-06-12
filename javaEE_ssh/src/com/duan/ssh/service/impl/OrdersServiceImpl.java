package com.duan.ssh.service.impl;

import com.duan.ssh.dao.OrdersDao;
import com.duan.ssh.entity.Order;
import com.duan.ssh.entity.OrderGoods;
import com.duan.ssh.model.PageBean;
import com.duan.ssh.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.service.impl
 * @ClassName: OrdersDaoImpl
 * @Author: duan
 * @Description: 订单服务
 * @Date: 2019-05-31 15:24
 * @Version: 1.0
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    public OrdersDao getOrdersDao() {
        return ordersDao;
    }

    public void setOrdersDao(OrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    @Override
    public int insertOrders(Order order) throws Exception {

        order.setCreatetime(new Timestamp(new Date().getTime()));

        int result = ordersDao.insertOrders(order);

        return result;
    }

    @Override
    public int updateOrders(Order order) throws Exception {

        order.setCreatetime(new Timestamp(new Date().getTime()));

        int resul = ordersDao.updateOrders(order);

        return resul;
    }

    @Override
    public int deleteOrders(Order order) throws Exception {

        int resul = ordersDao.deleteOrders(order);

        return resul;
    }

    @Override
    public List<Order> selectAllOrders() throws Exception {

        List<Order> orderList = ordersDao.selectAllOrders();

        return orderList;
    }

    @Override
    public Order getOrdersById(int id) throws Exception {

        Order order = ordersDao.getOrdersById(id);

        return order;
    }

    @Override
    public Set<OrderGoods> selectOG(int id) throws Exception {

        Set<OrderGoods> setOG = ordersDao.selectOG(id);

        return setOG;
    }

//    @Override
//    public List<Order> getOrdersByUname(String username) throws Exception {
//
//        List<Order> oList = ordersDao.getOrdersByUname(username);
//
//        return oList;
//    }

    @Override
    public List<Order> getOrdersByPrice(Integer low, Integer high) throws Exception {

        List<Order> oList = ordersDao.getOrdersByPrice(low, high);

        return oList;
    }

    @Override
    public PageBean<Order> getOrderByPage(int pageNum, int pageSize) throws Exception {

        int totalRecord = ordersDao.orderCount();

        PageBean pb = new PageBean(pageNum, pageSize, totalRecord);

        pb.setList(ordersDao.pageOrderList(pb.getStartIndex(), pageSize));

        return pb;
    }
}

