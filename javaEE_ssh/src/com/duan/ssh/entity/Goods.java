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
 * @ClassName: Goods
 * @Author: duan
 * @Description:
 * @Date: 2019-05-30 12:05
 * @Version: 1.0
 */

@Entity
public class Goods {
    private int id;
    private String name;
    private Integer price;
    private String type;
    private String img;
    private Integer count;
    private Timestamp createtime;
    private Set<OrderGoods> setOrderGoods = new HashSet<OrderGoods>();
    private Set<Shoppingcart> setShoppingcart = new HashSet<Shoppingcart>();

    @JSON(serialize = false)
    public Set<Shoppingcart> getSetShoppingcart() {
        return setShoppingcart;
    }

    public void setSetShoppingcart(Set<Shoppingcart> setShoppingcart) {
        this.setShoppingcart = setShoppingcart;
    }

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
        Goods goods = (Goods) o;
        return id == goods.id &&
                Objects.equals(name, goods.name) &&
                Objects.equals(price, goods.price) &&
                Objects.equals(type, goods.type) &&
                Objects.equals(img, goods.img) &&
                Objects.equals(count, goods.count) &&
                Objects.equals(createtime, goods.createtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, type, img, count, createtime);
    }
}
