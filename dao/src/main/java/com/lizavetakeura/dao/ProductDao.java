package com.lizavetakeura.dao;

import com.lizavetakeura.api.dao.IProductDao;
import com.lizavetakeura.model.Product;
import com.lizavetakeura.model.Subcategory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao extends AbstractDao<Product> implements IProductDao {

    private ProductDao() {
        super(Product.class);
    }

    public List<Product> getProductsFromOneSubcategory(Subcategory subcategory) {

        List<Product> products = new ArrayList<Product>();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get("subcategory"), subcategory));
        TypedQuery<Product> newQuery = entityManager.createQuery(query);
        products = newQuery.getResultList();

        return products;
    }
}
