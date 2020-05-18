package com.lizavetakeura.controller;

import com.lizavetakeura.api.service.ISubcategoryService;
import com.lizavetakeura.service.dto.SubcategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subcategories")
public class SubcategoryController {

    private ISubcategoryService subcategoryService;

    @Autowired
    public void setSubcategoryService(ISubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("/all")
    public List<SubcategoryDTO> getAllSubategories() {
        return subcategoryService.getAllSubcategories();
    }

    @GetMapping("/{id}")
    public SubcategoryDTO getSubategoryById(@PathVariable Integer id) {
        return (SubcategoryDTO) subcategoryService.subcategoryDTO(id);
    }

    @GetMapping("/category/{category_id}")
    public List<SubcategoryDTO> getSubategoriesFromCategory(@PathVariable Integer category_id) {
        return subcategoryService.getSubcategoriesByCategoryId(category_id);
    }
}
