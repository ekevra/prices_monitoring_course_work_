package com.lizavetakeura.service;

import com.lizavetakeura.api.dao.ICategoryDao;
import com.lizavetakeura.api.service.ICategoryService;
import com.lizavetakeura.model.Category;
import com.lizavetakeura.service.dto.CategoryDTO;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService<CategoryDTO> {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);
    private ICategoryDao categoryDao;

    public CategoryService() {
    }

    @Autowired
    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        try {
            categories = categoryDao.getAll();
        } catch (HibernateException e) {
            logger.log(Level.ERROR, "The import failed." + e.getMessage());
            throw new ServiceException("Some problems with getting information.", e.getCause());
        }
        return categories;
    }

    public Category getCategoryById(Integer id){
        Category category = new Category();
        try {
            category = categoryDao.getById(id);
        } catch (HibernateException e) {
            logger.log(Level.ERROR, "The import failed." + e.getMessage());
            throw new ServiceException("Some problems with getting information.", e.getCause());
        }
        return category;
    }

    public List<CategoryDTO> categoriesDto() {
        List<Category> categories = getAllCategories();
        ModelMapper modelMapper = new ModelMapper();
        List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
        for (Category category : categories) {
            categoryDTOs.add(modelMapper.map(category, CategoryDTO.class));
        }
        return categoryDTOs;
    }

    public CategoryDTO categoryDTO(Integer id){
        Category category = getCategoryById(id);
        ModelMapper modelMapper = new ModelMapper();
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    @Transactional
    public void createCategory(CategoryDTO categoryDTO) {
        try{
            categoryDao.create(getCategory(categoryDTO));
        }
        catch (HibernateException e){
            logger.log(Level.ERROR, "Creating failed." + e.getMessage());
            throw new ServiceException("Creating failed.", e.getCause());
        }
    }

    @Transactional
    public void updateCategory(CategoryDTO categoryDTO) {

        Category newCategory = getCategory(categoryDTO);
        Category oldCategory = categoryDao.getById(newCategory.getId());
        if(oldCategory != null){
            oldCategory = newCategory;
            try{
                categoryDao.update(oldCategory);
            }
            catch (HibernateException e){
                logger.log(Level.ERROR, "Updating failed." + e.getMessage());
                throw new ServiceException("Updating failed.", e.getCause());
            }
        }
    }

    @Transactional
    public void deleteCategory(Integer id) {
        Category category = categoryDao.getById(id);
        if(category != null){
            try{
                categoryDao.delete(category);
            }
            catch (HibernateException e){
                logger.log(Level.ERROR, "Deleting failed." + e.getMessage());
                throw new ServiceException("Deleting failed.", e.getCause());
            }
        }
    }

    private Category getCategory(CategoryDTO categoryDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(categoryDTO, Category.class);
    }
}
