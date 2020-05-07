package com.lizavetakeura.service.dto;

import java.util.List;

public class ProductDTO {

    private Integer id;

    private String name;

    private String manufacture;

    private Double weight;

    private SubcategoryDTO subcategory;

    private PriceDTO price;

    private List<StoreDTO> stores;

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

    public SubcategoryDTO getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubcategoryDTO subcategory) {
        this.subcategory = subcategory;
    }

    public PriceDTO getPrice() {
        return price;
    }

    public void setPrice(PriceDTO price) {
        this.price = price;
    }

    public List<StoreDTO> getStores() {
        return stores;
    }

    public void setStores(List<StoreDTO> stores) {
        this.stores = stores;
    }
}
