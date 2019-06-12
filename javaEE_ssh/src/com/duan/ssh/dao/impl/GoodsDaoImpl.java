package com.duan.ssh.dao.impl;

import com.duan.ssh.dao.GoodsDao;
import com.duan.ssh.entity.Goods;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.dao.impl
 * @ClassName: GoodsDaoImpl
 * @Author: duan
 * @Description: 商品数据操作
 * @Date: 2019-05-31 15:29
 * @Version: 1.0
 */
@Repository
public class GoodsDaoImpl implements GoodsDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

//    @Autowired
//    private SessionFactory sessionFactory;

    Session session = null;
    Transaction tx = null;

    @Override
    public int insertGoods(Goods goods) throws Exception {

        try {
            session = hibernateTemplate.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.save(goods);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    @Override
    public int updateGoods(Goods goods) throws Exception {

        try {
            session = hibernateTemplate.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.update(goods);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    @Override
    public int deleteGoods(Goods goods) throws Exception {
        try {
            session = hibernateTemplate.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.delete(goods);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public List<Goods> selectAllGoods() throws Exception {

        List<Goods> goodsList = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Goods.class);
            goodsList = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return goodsList;
    }

    @Override
    public List<Goods> findGoodsByName(String goods_name) throws Exception {

        List<Goods> goodsList = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Goods.class);
            criteria.add(Restrictions.like("name", "%" + goods_name + "%"));
//            criteria.add(Restrictions.);
            goodsList = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return goodsList;

    }

    @Override
    public List<Goods> selectGoodsByType(String type) throws Exception {

        List<Goods> goodsList = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Goods.class);
            criteria.add(Restrictions.eq("type", type));
            goodsList = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return goodsList;

    }

    @Override
    public Goods getGoodsById(int id) throws Exception {
        Goods goods = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            goods = session.get(Goods.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return goods;
    }

    @Override
    public List<Goods> selectGoodsPriceBetween(Integer low, Integer high) throws Exception {

        List<Goods> goodsList = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Goods.class);
            criteria.add(Restrictions.between("price", low, high == null ? 50000000 : high));
            goodsList = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return goodsList;

    }

    @Override
    public int goodsCount() throws Exception {

        int count = 0;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            String hql = "select count(*) from Goods";
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
    public List<Goods> pageGoodsList(int startIndex, int pageSize) throws Exception {

        List<Goods> goodsList = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Goods.class);
            criteria.addOrder(Order.desc("id"));
            criteria.setFirstResult(startIndex);
            criteria.setMaxResults(pageSize);
            goodsList = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return goodsList;

    }


}
