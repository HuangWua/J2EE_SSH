package com.duan.ssh.service;

import com.duan.ssh.entity.Shoppingcart;

import java.util.List;

public interface ShoppingCartService {

    public int insertShoppingcart(Shoppingcart sc) throws Exception;

    public int updateShoppingcart(Shoppingcart sc) throws Exception;

    public int deleteShoppingcart(Shoppingcart sc) throws Exception;

    public List<Shoppingcart> selectAllShoppingcart() throws Exception;

    public Shoppingcart getShoppingcartById(int id) throws Exception;

}
