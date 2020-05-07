package com.lizavetakeura.model.helpers;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "product_store")
public class StoreProduct implements Serializable {

    @Id
    private Integer store_id;

    @Id
    private Integer product_id;

    public StoreProduct() {
    }

    public StoreProduct(Integer store_id, Integer product_id) {
        this.store_id = store_id;
        this.product_id = product_id;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
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

        result = prime * result + ((store_id == null) ? 0 : store_id.hashCode());
        result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        StoreProduct storeProduct = (StoreProduct) object;
        return store_id.equals(storeProduct.getStore_id()) &&
                product_id.equals(storeProduct.getProduct_id());
    }
}
