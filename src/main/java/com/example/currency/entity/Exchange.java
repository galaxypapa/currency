package com.example.currency.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "exchange")
public class Exchange {

    @Id
    private String id;

    @Column
    private String baseCurrency;

    @Column
    private String toCurrency;

    @Column
    private Double rate;

    @Temporal(TemporalType.DATE)
    @Column
    private Date rateDate;

    @Column
    private Long rateTimeStamp;

    @Column(updatable = false)
    private Long createdAt;

    @Column
    private Long updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }

    public Long getRateTimeStamp() {
        return rateTimeStamp;
    }

    public void setRateTimeStamp(Long rateTimeStamp) {
        this.rateTimeStamp = rateTimeStamp;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void preInsert() {
        setCreatedAt(System.currentTimeMillis());
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedAt(System.currentTimeMillis());
    }
}
