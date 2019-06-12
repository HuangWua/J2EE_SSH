package com.duan.ssh.dao.impl;

import com.duan.ssh.dao.UserDao;
import com.duan.ssh.entity.Order;
import com.duan.ssh.entity.Shoppingcart;
import com.duan.ssh.entity.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.dao.impl
 * @ClassName: UserDaoImpl
 * @Author: duan
 * @Description: 用户持久层
 * @Date: 2019-05-29 12:18
 * @Version: 1.0
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
//    @Autowired
//    private HibernateTemplate hibernateTemplate;

    @Override
    public int insertUser(User user) throws Exception {

        int result = 0;

        Transaction tx = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            result = 1;
        } catch (HibernateException e) {
            tx.rollback();
            result = 0;
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getUserById(int id) throws Exception {
        User user = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            user = session.get(User.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public User getUserByName(String name) throws Exception {

        User user = null;

        List<User> userList = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();

            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", name));

            userList = criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        user = userList.size() == 0 ? null : userList.get(0);

        return user;
    }

    @Override
    public Set<Shoppingcart> selectSc(int id) throws Exception {

        Set<Shoppingcart> setSc = null;

        Session session = null;
        try {
            session = sessionFactory.openSession();

            setSc = session.get(User.class, id).getSetShoppingcart();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return setSc;
    }

    @Override
    public int updateUser(User user) throws Exception {

        int result = 0;

        Transaction tx = null;

        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
            result = 1;
        } catch (HibernateException e) {
            tx.rollback();
            result = 0;
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Set<Order> selectOrders(int id) throws Exception {

        Set<Order> setO = null;

        Session session = null;
        try {
            session = sessionFactory.openSession();

            setO = session.get(User.class, id).getSetOrder();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return setO;

    }

    @Override
    public List<User> selectAllUser() throws Exception {

        List<User> userList = null;

        Session session = null;
        try {
            session = sessionFactory.openSession();

            Criteria criteria = session.createCriteria(User.class);

            userList = criteria.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return userList;
    }

    @Override
    public int deleteUser(User user) throws Exception {

        int result = 0;

        Transaction tx = null;

        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            session.delete(session.get(User.class, user.getId())); //session.get(User.class, user.getId())
            tx.commit();
            result = 1;
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            result = 0;
        }

        return result;

    }

    @Override
    public List<User> selectUserLikeName(String registerName) throws Exception {

        List<User> userList = null;

        Session session = null;
        try {
            session = sessionFactory.openSession();

            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.like("username", "%" + registerName + "%"));

            userList = criteria.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return userList;

    }

    @Override
    public List<User> selectUserByRole(int role) throws Exception {

        List<User> userList = null;

        Session session = null;
        try {
            session = sessionFactory.openSession();

            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("role", role));

            userList = criteria.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return userList;

    }

    @Override
    public int userCount() throws Exception {

        int count = 0;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String hql = "select count(*) from User";
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
    public List<User> pageUserList(int startIndex, int pageSize) throws Exception {

        List<User> userList = null;

        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class);
            criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
            criteria.setFirstResult(startIndex);
            criteria.setMaxResults(pageSize);
            userList = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return userList;
    }


}
