package com.lizavetakeura.service;

import com.lizavetakeura.api.dao.ISubcategoryDao;
import com.lizavetakeura.api.service.ICategoryService;
import com.lizavetakeura.api.service.ISubcategoryService;
import com.lizavetakeura.model.Category;
import com.lizavetakeura.model.Subcategory;
import com.lizavetakeura.service.dto.SubcategoryDTO;
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
public class SubcategoryService implements ISubcategoryService<SubcategoryDTO> {

    private static final Logger logger = LogManager.getLogger(SubcategoryService.class);
    private ISubcategoryDao subcategoryDao;
    private ICategoryService categoryService;

    public SubcategoryService() {
    }

    public List<SubcategoryDTO> getAllSubcategories(){
        List<Subcategory> subcategories = new ArrayList<Subcategory>();
        try {
            subcategories = subcategoryDao.getAll();
        } catch (HibernateException e) {
            logger.log(Level.ERROR, "The import failed." + e.getMessage());
            throw new ServiceException("Some problems with getting information.", e.getCause());
        }
        return subcategoriesDto(subcategories);
    }

    public Subcategory getSubcategoryById(Integer id){
        Subcategory subcategory = new Subcategory();
        try {
            subcategory = subcategoryDao.getById(id);

        } catch (HibernateException e) {
            logger.log(Level.ERROR, "The import failed." + e.getMessage());
            throw new ServiceException("Some problems with getting information.", e.getCause());
        }
        return subcategory;
    }

    public List<SubcategoryDTO> getSubcategoriesByCategoryId(Integer id){
        Category category = categoryService.getCategoryById(id);
        List<Subcategory> subcategories = getSubcategoriesFromOneCategory(category);
        return subcategoriesDto(subcategories);
    }

    public List<Subcategory> getSubcategoriesFromOneCategory(Category category){
        List<Subcategory> subcategories = new ArrayList<Subcategory>();
        try {
            subcategories = subcategoryDao.getSubcategoriesFromOneCategory(category);

        } catch (HibernateException e) {
            logger.log(Level.ERROR, "The import failed." + e.getMessage());
            throw new ServiceException("Some problems with getting information.", e.getCause());
        }
        return subcategories;
    }

    public List<SubcategoryDTO> subcategoriesDto(List<Subcategory> subcategories) {
        ModelMapper modelMapper = new ModelMapper();
        List<SubcategoryDTO> subcategoryDTOs = new ArrayList<SubcategoryDTO>();
        for (Subcategory subcategory : subcategories) {
            subcategoryDTOs.add(modelMapper.map(subcategory, SubcategoryDTO.class));
        }
        return subcategoryDTOs;
    }

    public SubcategoryDTO subcategoryDTO(Integer id){
        Subcategory subcategory = getSubcategoryById(id);
        ModelMapper modelMapper = new ModelMapper();
        SubcategoryDTO subcategoryDTO = modelMapper.map(subcategory, SubcategoryDTO.class);
        return subcategoryDTO;
    }

    @Autowired
    public void setSubcategoryDao(ISubcategoryDao subcategoryDao) {
        this.subcategoryDao = subcategoryDao;
    }

    @Autowired
    public void setCategoryService(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
