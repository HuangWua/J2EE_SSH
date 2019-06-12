package com.duan.ssh.service.impl;

import com.duan.ssh.dao.ShoppingCartDao;
import com.duan.ssh.entity.Shoppingcart;
import com.duan.ssh.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.service.impl
 * @ClassName: ShoppingCarServiceImpl
 * @Author: duan
 * @Description: 购物车服务
 * @Date: 2019-05-31 15:26
 * @Version: 1.0
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    public ShoppingCartDao getShoppingCartDao() {
        return shoppingCartDao;
    }

    public void setShoppingCartDao(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    public int insertShoppingcart(Shoppingcart sc) throws Exception {

        sc.setCreatetime(new Timestamp(new Date().getTime()));

        int resul = shoppingCartDao.insertShoppingcart(sc);

        return resul;
    }

    @Override
    public int updateShoppingcart(Shoppingcart sc) throws Exception {

        sc.setCreatetime(new Timestamp(new Date().getTime()));

        int resul = shoppingCartDao.updateShoppingcart(sc);

        return resul;
    }

    @Override
    public int deleteShoppingcart(Shoppingcart sc) throws Exception {

        int resul = shoppingCartDao.deleteShoppingcart(sc);

        return resul;
    }

    @Override
    public List<Shoppingcart> selectAllShoppingcart() throws Exception {

        List<Shoppingcart> scList = shoppingCartDao.selectAllShoppingcart();

        return scList;
    }

    @Override
    public Shoppingcart getShoppingcartById(int id) throws Exception {

        Shoppingcart sc = shoppingCartDao.getShoppingcartById(id);

        return sc;
    }
}
