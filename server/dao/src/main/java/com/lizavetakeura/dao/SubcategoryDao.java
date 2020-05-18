package com.lizavetakeura.dao;

import com.lizavetakeura.api.dao.ISubcategoryDao;
import com.lizavetakeura.model.Category;
import com.lizavetakeura.model.Subcategory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubcategoryDao extends AbstractDao<Subcategory> implements ISubcategoryDao {

    private SubcategoryDao() {
        super(Subcategory.class);
    }

    public List<Subcategory> getSubcategoriesFromOneCategory(Category category) {

        List<Subcategory> subcategories = new ArrayList<Subcategory>();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Subcategory> query = criteriaBuilder.createQuery(Subcategory.class);
        Root<Subcategory> root = query.from(Subcategory.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get("category"), category));
        TypedQuery<Subcategory> newQuery = entityManager.createQuery(query);
        subcategories = newQuery.getResultList();

        return subcategories;

    }
}
