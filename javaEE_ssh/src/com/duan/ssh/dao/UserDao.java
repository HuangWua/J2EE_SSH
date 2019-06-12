package com.duan.ssh.dao;

import com.duan.ssh.entity.Order;
import com.duan.ssh.entity.Shoppingcart;
import com.duan.ssh.entity.User;

import java.util.List;
import java.util.Set;

public interface UserDao {

    public int insertUser(User user) throws Exception;

    public User getUserById(int id) throws Exception;

    public User getUserByName(String name) throws Exception;

    public Set<Shoppingcart> selectSc(int id) throws Exception;

    public int updateUser(User user) throws Exception;

    public Set<Order> selectOrders(int id) throws Exception;

    public List<User> selectAllUser() throws Exception;

    public int deleteUser(User user) throws Exception;

    public List<User> selectUserLikeName(String registerName) throws Exception;

    public List<User> selectUserByRole(int role) throws Exception;

    public int userCount() throws Exception;

    public List<User> pageUserList(int startIndex, int pageSize) throws Exception;

}
