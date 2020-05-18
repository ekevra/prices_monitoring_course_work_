package com.lizavetakeura.dao;

import com.lizavetakeura.api.dao.IOldPriceDao;
import com.lizavetakeura.model.helpers.OldPrice;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OldPriceDao extends AbstractDao<OldPrice> implements IOldPriceDao {

    private OldPriceDao() {
        super(OldPrice.class);
    }

    public List<OldPrice> getPricesByProductId(Integer productId){
        List<OldPrice> oldPrices = new ArrayList<OldPrice>();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OldPrice> query = criteriaBuilder.createQuery(OldPrice.class);
        Root<OldPrice> root = query.from(OldPrice.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get("product_id"), productId));
        TypedQuery<OldPrice> newQuery = entityManager.createQuery(query);
        oldPrices = newQuery.getResultList();

        return oldPrices;
    }
}
