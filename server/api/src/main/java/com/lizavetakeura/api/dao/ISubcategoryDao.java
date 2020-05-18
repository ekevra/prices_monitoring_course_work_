package com.lizavetakeura.api.dao;

import com.lizavetakeura.model.Category;
import com.lizavetakeura.model.Subcategory;

import java.util.List;

public interface ISubcategoryDao extends GenericDao<Subcategory> {

    List<Subcategory> getSubcategoriesFromOneCategory(Category category);
}
