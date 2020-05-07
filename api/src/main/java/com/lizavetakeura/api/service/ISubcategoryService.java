package com.lizavetakeura.api.service;

import com.lizavetakeura.model.Category;
import com.lizavetakeura.model.Subcategory;

import java.util.List;

public interface ISubcategoryService<T> {

    public List<T> getAllSubcategories();

    public Subcategory getSubcategoryById(Integer id);

    public List<T> getSubcategoriesByCategoryId(Integer id);

    public List<Subcategory> getSubcategoriesFromOneCategory(Category category);

    public List<T> subcategoriesDto(List<Subcategory> subcategories);

    public T subcategoryDTO(Integer id);
}
