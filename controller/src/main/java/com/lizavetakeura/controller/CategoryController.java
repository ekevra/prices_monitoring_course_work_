package com.lizavetakeura.controller;

import com.lizavetakeura.api.service.ICategoryService;
import com.lizavetakeura.service.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private ICategoryService categoryService;

    @Autowired
    public void setCategoryService(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.categoriesDto();
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Integer id) {
        return (CategoryDTO) categoryService.categoryDTO(id);
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> add(@RequestBody CategoryDTO categoryDTO){
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

    @PostMapping("/update")
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategoryById(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }
}
