package com.duan.ssh.service;

import com.duan.ssh.entity.Order;
import com.duan.ssh.entity.Shoppingcart;
import com.duan.ssh.entity.User;
import com.duan.ssh.model.PageBean;

import java.util.List;
import java.util.Set;

public interface UserService {
    public void Hello();

    public int registerUser(User user) throws Exception;

    public User getUserById(int id) throws Exception;

    public User selectUserByName(String name) throws Exception;

    public Set<Shoppingcart> selectSc(int id) throws Exception;

    public int updateUser(User user) throws Exception;

    public Set<Order> selectOrders(int id) throws Exception;

    public List<User> selectAllUser() throws Exception;

    public int deleteUser(User user) throws Exception;

    public List<User> selectUserLikeName(String registerName) throws Exception;

    public List<User> selectUserByRole(int role) throws Exception;

    public int userPayment(User user) throws Exception;

    public PageBean<User> getUserByPage(int pageNum, int pageSize) throws Exception;


}
