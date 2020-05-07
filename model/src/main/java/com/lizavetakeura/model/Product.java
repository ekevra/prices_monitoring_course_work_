package com.lizavetakeura.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private String manufacture;

    @Column
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;

    @OneToOne
    private Price price;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private List<Store> stores;

    public Product() {
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

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return  name.equals(product.getName()) &&
                id.equals(product.getId()) &&
                manufacture.equals(product.getManufacture())&&
                price.equals(product.getPrice())&&
                stores.equals(product.getStores()) &&
                subcategory.equals(product.getSubcategory())&&
                weight.equals(product.getWeight());
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((manufacture == null) ? 0 : manufacture.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        result = prime * result + ((subcategory == null) ? 0 : subcategory.hashCode());
        result = prime * result + ((stores == null) ? 0 : stores.hashCode());
        result = prime * result + id;

        return result;
    }
}
