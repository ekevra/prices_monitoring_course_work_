package com.lizavetakeura.api.dao;

import com.lizavetakeura.model.helpers.StoreProduct;

import java.util.List;

public interface IStoreProductDao extends GenericDao<StoreProduct> {

    public List<StoreProduct> getStoreProductByProductId(Integer productId);
}
