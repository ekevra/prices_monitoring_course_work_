package com.lizavetakeura.api.service;

import com.lizavetakeura.model.Category;

import java.util.List;

public interface ICategoryService<T> {

    public Category getCategoryById(Integer id);

    public List<Category> getAllCategories();

    public List<T> categoriesDto();

    public T categoryDTO(Integer id);

    public void createCategory(T category);

    public void updateCategory(T category);

    public void deleteCategory(Integer id);
}
