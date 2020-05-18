package com.lizavetakeura.model.helpers;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Table(name = "old_prices")
public class OldPrice implements Serializable {

    @Id
    private Integer price_id;

    @Id
    private Integer product_id;

    public OldPrice() {
    }

    public OldPrice(Integer price_id, Integer product_id) {
        this.price_id = price_id;
        this.product_id = product_id;
    }

    public Integer getPrice_id() {
        return price_id;
    }

    public void setPrice_id(Integer price_id) {
        this.price_id = price_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + ((price_id == null) ? 0 : price_id.hashCode());
        result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OldPrice oldPrice = (OldPrice) object;
        return product_id.equals(oldPrice.getProduct_id()) &&
                price_id.equals(oldPrice.getPrice_id());
    }
}
