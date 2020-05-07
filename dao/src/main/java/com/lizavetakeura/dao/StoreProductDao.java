package com.lizavetakeura.dao;

import com.lizavetakeura.api.dao.GenericDao;
import com.lizavetakeura.api.dao.IStoreProductDao;
import com.lizavetakeura.model.helpers.OldPrice;
import com.lizavetakeura.model.helpers.StoreProduct;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StoreProductDao extends AbstractDao<StoreProduct> implements IStoreProductDao {

    private StoreProductDao() {
        super(StoreProduct.class);
    }

    public List<StoreProduct> getStoreProductByProductId(Integer productId){
        List<StoreProduct> storeProductList = new ArrayList<StoreProduct>();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StoreProduct> query = criteriaBuilder.createQuery(StoreProduct.class);
        Root<StoreProduct> root = query.from(StoreProduct.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get("product_id"), productId));
        TypedQuery<StoreProduct> newQuery = entityManager.createQuery(query);
        storeProductList = newQuery.getResultList();

        return storeProductList;
    }
}
