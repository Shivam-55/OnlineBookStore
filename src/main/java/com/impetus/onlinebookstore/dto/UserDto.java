package com.impetus.onlinebookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.impetus.onlinebookstore.entity.Cart;
import com.impetus.onlinebookstore.entity.Purchase;
import java.util.Date;
import java.util.List;

public class UserDto {
    private  long id;
    private String userName;
    private String password;
    private List<Purchase> purchases;
    private Cart cart;
    private Date createDate;
    private Date modifyDate;

    public UserDto(long id, String userName, String password, List<Purchase> purchases, Cart cart, Date createDate, Date modifyDate) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.purchases = purchases;
        this.cart = cart;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public List<Purchase> getPurchases() {
        return purchases;
    }

    @JsonProperty
    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @JsonIgnore
    public Cart getCart() {
        return cart;
    }

    @JsonProperty
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}

