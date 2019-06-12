package com.duan.ssh.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.entity
 * @ClassName: OrderGoods
 * @Author: duan
 * @Description:
 * @Date: 2019-05-30 12:05
 * @Version: 1.0
 */

@Entity
public class OrderGoods {
    private int id;
    private int goodsCount;
    private Order order;
    private Goods goods;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Basic
    @Column(name = "goods_count")
    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderGoods that = (OrderGoods) o;
        return goodsCount == that.goodsCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsCount);
    }
}
