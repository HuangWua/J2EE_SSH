package com.duan.ssh.service.impl;

import com.duan.ssh.dao.UserDao;
import com.duan.ssh.entity.Order;
import com.duan.ssh.entity.Shoppingcart;
import com.duan.ssh.entity.User;
import com.duan.ssh.model.PageBean;
import com.duan.ssh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.service.impl
 * @ClassName: UserServiceImpl
 * @Author: duan
 * @Description: 用户服务
 * @Date: 2019-05-28 22:45
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void Hello() {
        System.out.println("hello world");
    }

    @Override
    public int registerUser(User user) throws Exception {
        int result = userDao.insertUser(user);
        return result;
    }

    @Override
    public User getUserById(int id) throws Exception {
        return userDao.getUserById(id);
    }

    @Override
    public User selectUserByName(String name) throws Exception {

        return userDao.getUserByName(name);

    }

    @Override
    public Set<Shoppingcart> selectSc(int id) throws Exception {

        Set<Shoppingcart> setSc = userDao.selectSc(id);

        return setSc;
    }

    @Override
    public int updateUser(User user) throws Exception {
        int result = userDao.updateUser(user);
        return result;
    }

    @Override
    public Set<Order> selectOrders(int id) throws Exception {

        Set<Order> setO = userDao.selectOrders(id);

        return setO;
    }

    @Override
    public List<User> selectAllUser() throws Exception {

        List<User> userList = userDao.selectAllUser();

        return userList;
    }

    @Override
    public int deleteUser(User user) throws Exception {

        int result = userDao.deleteUser(user);

        return result;
    }

    @Override
    public List<User> selectUserLikeName(String registerName) throws Exception {

        List<User> userList = userDao.selectUserLikeName(registerName);

        return userList;
    }

    @Override
    public List<User> selectUserByRole(int role) throws Exception {

        List<User> userList = userDao.selectUserByRole(role);

        return userList;
    }

    @Override
    public int userPayment(User user) throws Exception {

        User userMsg = null;
        int result = 0;

        try {
            userMsg = userDao.getUserById(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        if (userMsg.getMoney() < user.getMoney()) {
            result = 2;
        } else {
            userMsg.setMoney(userMsg.getMoney() - user.getMoney());
            result = userDao.updateUser(userMsg);
        }

        return result;
    }

    @Override
    public PageBean<User> getUserByPage(int pageNum, int pageSize) throws Exception {

        int totalRecord = userDao.userCount();

        PageBean pb = new PageBean(pageNum, pageSize, totalRecord);

        pb.setList(userDao.pageUserList(pb.getStartIndex(), pageSize));

        return pb;

    }

}
