package com.lizavetakeura.dao;

import com.lizavetakeura.api.dao.ICategoryDao;
import com.lizavetakeura.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao extends AbstractDao<Category> implements ICategoryDao {

    private CategoryDao() {
        super(Category.class);
    }
}
