package com.lizavetakeura.dao;

import com.lizavetakeura.api.dao.IStoreDao;
import com.lizavetakeura.model.Store;
import org.springframework.stereotype.Repository;

@Repository
public class StoreDao extends AbstractDao<Store> implements IStoreDao {

    private StoreDao() {
        super(Store.class);
    }
}
