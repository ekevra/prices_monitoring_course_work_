package com.lizavetakeura.controller;

import com.lizavetakeura.api.service.IStoreService;
import com.lizavetakeura.service.dto.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private IStoreService storeService;

    @Autowired
    public void setStoreService(IStoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/all")
    public List<StoreDTO> getAllCategories() {
        return storeService.storesDto();
    }

    @GetMapping("/{id}")
    public StoreDTO getCategoryById(@PathVariable Integer id) {
        return (StoreDTO) storeService.storeDTO(id);
    }
}
