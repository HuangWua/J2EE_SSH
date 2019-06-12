package com.duan.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.entity
 * @ClassName: Shoppingcart
 * @Author: duan
 * @Description:
 * @Date: 2019-05-30 12:05
 * @Version: 1.0
 */

@Entity
public class Shoppingcart {
    private int id;
    private Integer goodsCount;
    private Integer status;
    private Timestamp createtime;
    private User user;
    private Goods goods;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "goods_count")
    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "createtime")
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shoppingcart that = (Shoppingcart) o;
        return id == that.id &&
                Objects.equals(goodsCount, that.goodsCount) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createtime, that.createtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsCount, status, createtime);
    }

}
