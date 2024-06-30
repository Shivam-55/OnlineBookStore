package com.code.onlinebookstore.dto;

import com.code.onlinebookstore.entity.Cart;
import com.code.onlinebookstore.entity.Purchase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public class BookDto {
    private long id;
    private String bookName;
    private String authorName;
    private double price;
    private int quantity;
    private List<Purchase> purchases;
    private List<Cart> carts;
    private Date createDate;
    private Date modifyDate;
    public BookDto() {
    }
    public BookDto(long id, String bookName, String authorName, double price, int quantity, Date createDate, Date modifyDate) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.price = price;
        this.quantity = quantity;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
    public List<Cart> getCart() {
        return carts;
    }

    @JsonProperty
    public void setCart(List<Cart> carts) {
        this.carts = carts;
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
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
