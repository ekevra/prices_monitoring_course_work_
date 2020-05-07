package com.lizavetakeura.service.dto;

import java.util.Date;

public class PriceDTO {

    private Integer id;

    private Double price;

    private Date start_date;

    private Integer isActive;

    public PriceDTO() {
    }

    public PriceDTO(Integer id, Double price, Date start_date, Integer isActive) {
        this.id = id;
        this.price = price;
        this.start_date = start_date;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}
