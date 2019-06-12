package com.duan.ssh.dao;

import com.duan.ssh.entity.Shoppingcart;

import java.util.List;

public interface ShoppingCartDao {

    public int insertShoppingcart(Shoppingcart sc) throws Exception;

    public int updateShoppingcart(Shoppingcart sc) throws Exception;

    public int deleteShoppingcart(Shoppingcart scS) throws Exception;

    public List<Shoppingcart> selectAllShoppingcart() throws Exception;

    public Shoppingcart getShoppingcartById(int id) throws Exception;

}
