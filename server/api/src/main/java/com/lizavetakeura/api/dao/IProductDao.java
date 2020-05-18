package com.lizavetakeura.api.dao;

import com.lizavetakeura.model.Product;
import com.lizavetakeura.model.Subcategory;

import java.util.List;

public interface IProductDao extends GenericDao<Product> {

    List<Product> getProductsFromOneSubcategory(Subcategory subcategory);
}
