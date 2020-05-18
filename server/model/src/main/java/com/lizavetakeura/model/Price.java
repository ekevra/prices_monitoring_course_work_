package com.lizavetakeura.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "price")
public class Price implements Comparable<Price>{

    @Id
    private Integer id;

    @Column
    private Double price;

    @Column
    private Date start_date;

    @Column
    private Integer isActive;

    public Price() {
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Price price = (Price) object;
        return  this.price.equals(price.getPrice()) &&
                id.equals(price.getId()) &&
                start_date.equals(price.getStart_date())&&
                isActive.equals(price.getIsActive());
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
        result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
        result = prime * result + id;

        return result;
    }


    public int compareTo(Price that) {
        int dtCompare = this.start_date.compareTo(that.start_date);
        if (dtCompare != 0)
            return dtCompare;

        int idCompare = this.id.compareTo(that.id);
        if (idCompare != 0)
            return idCompare;

        int activeCompare = this.isActive.compareTo(that.isActive);
        if(activeCompare != 0)
            return activeCompare;

        return Double.compare(this.price, that.price);
    }
}
