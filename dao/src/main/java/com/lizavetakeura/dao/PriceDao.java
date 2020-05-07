package com.lizavetakeura.dao;

import com.lizavetakeura.api.dao.IPriceDao;
import com.lizavetakeura.model.Price;
import org.springframework.stereotype.Repository;

@Repository
public class PriceDao extends AbstractDao<Price> implements IPriceDao {

    private PriceDao() {
        super(Price.class);
    }
}
