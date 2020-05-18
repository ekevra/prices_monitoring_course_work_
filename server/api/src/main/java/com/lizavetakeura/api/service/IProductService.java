package com.lizavetakeura.api.service;

import com.lizavetakeura.model.Category;
import com.lizavetakeura.model.Price;
import com.lizavetakeura.model.Product;
import com.lizavetakeura.model.Subcategory;

import java.util.List;

public interface IProductService<T> {

    public List<T> getProductsDTOFromOneCategory(Integer id);

    public List<T> getProductsDTOFromOneSubategory(Integer id);

    public List<Product> getProductsFromOneCategory(Category category);

    public List<Product> getProductsFromOneSubcategory(Subcategory subcategory);

    public void deleteProduct(Integer id);

    public void createProduct(T productDTO);

    public void updateProduct(T productDTO);

    public List<T> getByNameOrManufacture(String name, String manufacture);
}
