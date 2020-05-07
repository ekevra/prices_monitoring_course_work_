package com.lizavetakeura.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "store")
public class Store {

    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String address;

    @ManyToMany
    @JoinTable(
            name = "product_store",
            joinColumns = { @JoinColumn(name = "store_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private List<Product> products;

    public Store() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Store store = (Store) object;
        return  name.equals(store.getName()) &&
                id.equals(store.getId()) &&
                city.equals(store.getCity())&&
                address.equals(store.getAddress()) &&
                products.equals(store.getProducts());
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((products == null) ? 0 : products.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + id;

        return result;
    }
}
