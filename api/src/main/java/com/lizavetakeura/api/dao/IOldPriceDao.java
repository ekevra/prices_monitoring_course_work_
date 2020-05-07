package com.lizavetakeura.api.dao;

import com.lizavetakeura.model.helpers.OldPrice;

import java.util.List;

public interface IOldPriceDao extends GenericDao<OldPrice> {

    public List<OldPrice> getPricesByProductId(Integer productId);
}
