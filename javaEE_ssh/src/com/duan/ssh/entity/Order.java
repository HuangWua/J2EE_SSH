package com.duan.ssh.entity;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.entity
 * @ClassName: Order
 * @Author: duan
 * @Description:
 * @Date: 2019-05-30 14:16
 * @Version: 1.0
 */

@Entity
public class Order {
    private int id;
    private Integer sumprice;
    private Timestamp createtime;
    private Integer orderStatus;
    private User user;
    private Set<OrderGoods> setOrderGoods = new HashSet<OrderGoods>();

    @JSON(serialize = false)
    public Set<OrderGoods> getSetOrderGoods() {
        return setOrderGoods;
    }

    public void setSetOrderGoods(Set<OrderGoods> setOrderGoods) {
        this.setOrderGoods = setOrderGoods;
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
    @Column(name = "sumprice")
    public Integer getSumprice() {
        return sumprice;
    }

    public void setSumprice(Integer sumprice) {
        this.sumprice = sumprice;
    }

    @Basic
    @Column(name = "createtime")
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "order_status")
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(sumprice, order.sumprice) &&
                Objects.equals(createtime, order.createtime) &&
                Objects.equals(orderStatus, order.orderStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sumprice, createtime, orderStatus);
    }

}
