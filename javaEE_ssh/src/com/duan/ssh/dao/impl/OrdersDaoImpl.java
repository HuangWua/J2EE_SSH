package com.duan.ssh.dao.impl;

import com.duan.ssh.dao.OrdersDao;
import com.duan.ssh.entity.Order;
import com.duan.ssh.entity.OrderGoods;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.dao.impl
 * @ClassName: OrdersDaoImpl
 * @Author: duan
 * @Description: 订单信息操作
 * @Date: 2019-05-31 15:34
 * @Version: 1.0
 */
@Repository
public class OrdersDaoImpl implements OrdersDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    Session session;
    Transaction tx;

    @Override
    public int insertOrders(Order order) throws Exception {

        int result = 0;
        try {
            session = hibernateTemplate.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.save(order);
            result = order.getId();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            result = 0;
        }

        return result;

    }

    @Override
    public int updateOrders(Order order) throws Exception {

        int result = 0;

        try {
            session = hibernateTemplate.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.update(order);
            tx.commit();
            result = 1;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            result = 0;
        }

        return result;

    }

    @Override
    public int deleteOrders(Order order) throws Exception {

        int result = 0;

        try {
            session = hibernateTemplate.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Order orderDel = session.get(Order.class, order.getId());
            session.delete(orderDel);
            tx.commit();
            result = 1;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            result = 0;
        }

        return result;

    }

    @Override
    public List<Order> selectAllOrders() throws Exception {

        List<Order> orderList = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Order.class);
            orderList = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return orderList;

    }

    @Override
    public Order getOrdersById(int id) throws Exception {

        Order order = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            order = session.get(Order.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return order;
    }

    @Override
    public Set<OrderGoods> selectOG(int id) throws Exception {

        Set<OrderGoods> setOG = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            setOG = session.get(Order.class, id).getSetOrderGoods();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return setOG;
    }

//    @Override
//    public List<Order> getOrdersByUname(String username) throws Exception {
//
//        List<Order> orderList = null;
//
//        try {
//            session = hibernateTemplate.getSessionFactory().openSession();
//            Criteria criteria = session.createCriteria(Order.class);
//            criteria.add(Restrictions.like("user.username", "%" + username + "%"));
//            orderList = criteria.list();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//
//        return orderList;
//
//    }

    @Override
    public List<Order> getOrdersByPrice(Integer low, Integer height) throws Exception {

        List<Order> orderList = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Order.class);
            criteria.add(Restrictions.between("sumprice", low, height == null ? 50000000 : height));
            orderList = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return orderList;

    }

    @Override
    public int orderCount() throws Exception {

        int count = 0;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            String hql = "select count(*) from Order";
            count = ((Number) session.createQuery(hql).uniqueResult()).intValue();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return count;
    }

    @Override
    public List<Order> pageOrderList(int startIndex, int pageSize) throws Exception {

        List<Order> orderList = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Order.class);
            criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
            criteria.setFirstResult(startIndex);
            criteria.setMaxResults(pageSize);
            orderList = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return orderList;

    }
}
