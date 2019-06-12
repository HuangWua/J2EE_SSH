package com.duan.ssh.entity;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.entity
 * @ClassName: User
 * @Author: duan
 * @Description:
 * @Date: 2019-05-30 12:05
 * @Version: 1.0
 */

@Entity
public class User {
    private int id;
    private String username;
    private String password;
    private String defaultPhone;
    private String defaultAddress;
    private String gender;
    private Integer money;
    private Integer role;
    private Set<Order> setOrder = new HashSet<Order>();

    @JSON(serialize = false)
    public Set<Shoppingcart> getSetShoppingcart() {
        return setShoppingcart;
    }

    public void setSetShoppingcart(Set<Shoppingcart> setShoppingcart) {
        this.setShoppingcart = setShoppingcart;
    }

    private Set<Shoppingcart> setShoppingcart = new HashSet<Shoppingcart>();

    @JSON(serialize = false)
    public Set<Order> getSetOrder() {
        return setOrder;
    }

    public void setSetOrder(Set<Order> setOrder) {
        this.setOrder = setOrder;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "defaultPhone")
    public String getDefaultPhone() {
        return defaultPhone;
    }

    public void setDefaultPhone(String defaultPhone) {
        this.defaultPhone = defaultPhone;
    }

    @Basic
    @Column(name = "defaultAddress")
    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "money")
    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Basic
    @Column(name = "role")
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(defaultPhone, user.defaultPhone) &&
                Objects.equals(defaultAddress, user.defaultAddress) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(money, user.money) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, defaultPhone, defaultAddress, gender, money, role);
    }
}
