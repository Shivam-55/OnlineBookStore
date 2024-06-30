package com.code.onlinebookstore.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseId;
    private double totalAmount;
    @ManyToOne
    private User user;

    @ManyToMany
    private List<Book> books;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    public Purchase() {
    }

    public Purchase(long purchaseId, double totalAmount, User user, List<Book> books, Date createDate, Date modifyDate) {
        this.purchaseId = purchaseId;
        this.totalAmount = totalAmount;
        this.user = user;
        this.books = books;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
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
        return "Purchase{" +
                "purchaseId=" + purchaseId +
                ", totalAmount=" + totalAmount +
                ", user=" + user +
                ", books=" + books +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
