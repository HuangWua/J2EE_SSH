package com.duan.ssh.dao.impl;

import com.duan.ssh.dao.ShoppingCartDao;
import com.duan.ssh.entity.Shoppingcart;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.dao.impl
 * @ClassName: ShoppingCartDaoImpl
 * @Author: duan
 * @Description: 购物车信息操作
 * @Date: 2019-05-31 15:35
 * @Version: 1.0
 */
@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    Session session = null;
    Transaction tx = null;

    @Override
    public int insertShoppingcart(Shoppingcart sc) throws Exception {

        try {
            session = hibernateTemplate.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query<Shoppingcart> query = session.createQuery("from Shoppingcart sc where sc.user=:user and sc.goods=:goods", Shoppingcart.class);
            query.setParameter("user", sc.getUser());
            query.setParameter("goods", sc.getGoods());
            List<Shoppingcart> scList = query.list();

            if (scList.size() != 0) {
                Shoppingcart shoppingcart = scList.get(0);
                shoppingcart.setGoodsCount(shoppingcart.getGoodsCount() + sc.getGoodsCount());
                session.saveOrUpdate(shoppingcart);
            } else {
                session.saveOrUpdate(sc);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    @Override
    public int updateShoppingcart(Shoppingcart sc) throws Exception {

        try {
            session = hibernateTemplate.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.update(sc);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    @Override
    public int deleteShoppingcart(Shoppingcart sc) throws Exception {

        try {
            session = hibernateTemplate.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.delete(sc);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    @Override
    public List<Shoppingcart> selectAllShoppingcart() throws Exception {

        List<Shoppingcart> sgList = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Shoppingcart.class);
            sgList = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return sgList;
    }

    @Override
    public Shoppingcart getShoppingcartById(int id) throws Exception {

        Shoppingcart sg = null;

        try {
            session = hibernateTemplate.getSessionFactory().openSession();
            sg = session.get(Shoppingcart.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return sg;
    }
}
