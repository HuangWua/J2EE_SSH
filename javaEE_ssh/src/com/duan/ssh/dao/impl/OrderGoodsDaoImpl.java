package com.duan.ssh.dao.impl;

import com.duan.ssh.dao.OrderGoodsDao;
import com.duan.ssh.entity.OrderGoods;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.dao.impl
 * @ClassName: OrderGoodsDaoImpl
 * @Author: duan
 * @Description: 订单商品信息数据操作
 * @Date: 2019-05-31 15:31
 * @Version: 1.0
 */
@Repository
public class OrderGoodsDaoImpl implements OrderGoodsDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    Session session = null;
    Transaction tx = null;

    @Override
    public int insertOrderGoods(OrderGoods orderGoods) throws Exception {

        try {
            session = hibernateTemplate.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.save(orderGoods);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    @Override
    public int updateOrderGoods(OrderGoods orderGoods) throws Exception {

        try {
            session = hibernateTemplate.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.update(orderGoods);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    @Override
    public int deleteOrderGoods(OrderGoods orderGoods) throws Exception {

        try {
            session = hibernateTemplate.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.delete(orderGoods);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    @Override
    public List<OrderGoods> selectAllOrderGoods() throws Exception {

        List<OrderGoods> ogList = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(OrderGoods.class);
            ogList = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return ogList;
    }

    @Override
    public OrderGoods getOrderGoodsById(int id) throws Exception {

        OrderGoods og = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            og = session.get(OrderGoods.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return og;
    }

    @Override
    public List<OrderGoods> selectOGByOrderId(int order_id) throws Exception {

        List<OrderGoods> ogList = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(OrderGoods.class);
            criteria.add(Restrictions.eq("order.id", order_id));
            ogList = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return ogList;

    }

    @Override
    public int deleteOGByOrderId(int order_id) throws Exception {

        int result = 0;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(OrderGoods.class);
            criteria.add(Restrictions.eq("order.id", order_id));
            List<OrderGoods> ogList = criteria.list();
            tx = session.beginTransaction();

            for (OrderGoods og : ogList) {
                session.delete(og);
            }
            tx.commit();
            result = 1;
        } catch (Exception e) {
            result = 0;
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return result;
    }
}
