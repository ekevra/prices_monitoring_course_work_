package com.lizavetakeura.api.service;

import com.lizavetakeura.model.Store;

import java.util.List;

public interface IStoreService<T> {

    public List<Store> getAllStores();

    public Store getStoreById(Integer id);

    public List<T> storesDto();

    public T storeDTO(Integer id);
}
