package com.lizavetakeura.service;

import com.lizavetakeura.api.dao.IStoreDao;
import com.lizavetakeura.api.service.IStoreService;
import com.lizavetakeura.model.Store;
import com.lizavetakeura.service.dto.StoreDTO;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService implements IStoreService<StoreDTO> {

    private static final Logger logger = LogManager.getLogger(StoreService.class);
    private IStoreDao storeDao;

    public StoreService() {
    }

    @Autowired
    public void setStoreDao(IStoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public List<Store> getAllStores() {
        List<Store> stores = new ArrayList<Store>();
        try {
            stores = storeDao.getAll();

        } catch (HibernateException e) {
            logger.log(Level.ERROR, "The import failed." + e.getMessage());
            throw new ServiceException("Some problems with getting information.", e.getCause());
        }
        return stores;
    }

    public Store getStoreById(Integer id) {
        Store store = new Store();
        try {
            store = storeDao.getById(id);
        } catch (HibernateException e) {
            logger.log(Level.ERROR, "The import failed." + e.getMessage());
            throw new ServiceException("Some problems with getting information.", e.getCause());
        }
        return store;
    }

    public List<StoreDTO> storesDto() {
        List<Store> stores = getAllStores();
        ModelMapper modelMapper = new ModelMapper();
        List<StoreDTO> storeDTOs = new ArrayList<StoreDTO>();
        for (Store store : stores) {
            storeDTOs.add(modelMapper.map(store, StoreDTO.class));
        }
        return storeDTOs;
    }

    public StoreDTO storeDTO(Integer id) {
        Store store = getStoreById(id);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(store, StoreDTO.class);
    }
}