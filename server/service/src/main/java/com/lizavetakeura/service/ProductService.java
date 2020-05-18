package com.lizavetakeura.service;

import com.lizavetakeura.api.dao.IProductDao;
import com.lizavetakeura.api.dao.IStoreProductDao;
import com.lizavetakeura.api.service.ICategoryService;
import com.lizavetakeura.api.service.IProductService;
import com.lizavetakeura.api.service.ISubcategoryService;
import com.lizavetakeura.model.Category;
import com.lizavetakeura.model.Product;
import com.lizavetakeura.model.Store;
import com.lizavetakeura.model.Subcategory;
import com.lizavetakeura.model.helpers.StoreProduct;
import com.lizavetakeura.service.dto.ProductDTO;
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
public class ProductService implements IProductService<ProductDTO> {

    private static final Logger logger = LogManager.getLogger(ProductService.class);
    private IProductDao productDao;
    private IStoreProductDao storeProductDao;
    private ISubcategoryService subcategoryService;
    private ICategoryService categoryService;

    public ProductService() {
    }

    @Autowired
    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }
    @Autowired
    public void setStoreProductDao(IStoreProductDao storeProductDao) {
        this.storeProductDao = storeProductDao;
    }
    @Autowired
    public void setSubcategoryService(ISubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }
    @Autowired
    public void setCategoryService(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<ProductDTO> getProductsDTOFromOneCategory(Integer id){

        Category category = categoryService.getCategoryById(id);
        List<ProductDTO> dtoProducts = new ArrayList<ProductDTO>();
        List<Product> products = getProductsFromOneCategory(category);
        ModelMapper modelMapper = new ModelMapper();
        for (Product product : products) {
            dtoProducts.add(modelMapper.map(product, ProductDTO.class));
        }
        return dtoProducts;
    }

    public List<ProductDTO> getProductsDTOFromOneSubategory(Integer id){

        Subcategory subcategory = subcategoryService.getSubcategoryById(id);
        List<ProductDTO> dtoProducts = new ArrayList<ProductDTO>();
        List<Product> products = getProductsFromOneSubcategory(subcategory);
        ModelMapper modelMapper = new ModelMapper();
        for (Product product : products) {
            dtoProducts.add(modelMapper.map(product, ProductDTO.class));
        }
        return dtoProducts;
    }

    public List<Product> getProductsFromOneCategory(Category category){
        List<Subcategory> subcategories = subcategoryService.getSubcategoriesFromOneCategory(category);
        List<Product> allProducts = new ArrayList<Product>();
        for(Subcategory subcategory : subcategories){
            List<Product> products = getProductsFromOneSubcategory(subcategory);
            for(Product product : products){
                allProducts.add(product);
            }
        }
        return allProducts;
    }

    @Transactional
    public List<Product> getProductsFromOneSubcategory(Subcategory subcategory){
        List<Product> products = new ArrayList<Product>();
        try {
            products = productDao.getProductsFromOneSubcategory(subcategory);
            logger.info("The import was successful.");

        } catch (HibernateException e) {
            logger.log(Level.ERROR, "The import failed." + e.getMessage());
            throw new ServiceException("Some problems with getting information.", e.getCause());
        }
        return products;
    }

    private Product fromDtoToEntity(ProductDTO productDTO){
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productDTO, Product.class);
        return product;
    }

    @Transactional
    public void createProduct(ProductDTO productDTO){
        Product product = fromDtoToEntity(productDTO);
        try{
            productDao.create(product);
        }
        catch (HibernateException e){
            logger.log(Level.ERROR, "Creating failed." + e.getMessage());
            throw new ServiceException("Some problems with creating.", e.getCause());
        }
    }

    @Transactional
    public void updateProduct(ProductDTO productDTO){
        Product newProduct = fromDtoToEntity(productDTO);
        Product oldProduct = productDao.getById(newProduct.getId());

        List<StoreProduct> storeProductList = new ArrayList<StoreProduct>();
        if(newProduct.getStores().size() != 0){
            for(Store store : newProduct.getStores()){
                storeProductList.add(new StoreProduct(store.getId(), newProduct.getId()));
            }
        }
        List<StoreProduct> oldStoreProductList = storeProductDao.getStoreProductByProductId(newProduct.getId());

        List<StoreProduct> suchStoreProducts = new ArrayList<StoreProduct>();

        for(StoreProduct oldStoreProduct : oldStoreProductList){
            for(StoreProduct newStoreProduct : storeProductList){
                if(oldStoreProduct.equals(newStoreProduct)){
                    suchStoreProducts.add(oldStoreProduct);
                }
            }
        }

        List<StoreProduct> storeProductsForDelete = new ArrayList<StoreProduct>();
        Integer count = 0;
        for(StoreProduct oldStoreProduct : oldStoreProductList){
            for(StoreProduct suchStoreProduct : suchStoreProducts){
                if(suchStoreProduct.equals(oldStoreProduct)){
                    count++;
                }
            }
            if(count == 0){
                storeProductsForDelete.add(oldStoreProduct);
            }
            else{
                count = 0;
            }
        }

        List<StoreProduct> storeProductsForCreate = new ArrayList<StoreProduct>();
        Integer createCount = 0;
        for(StoreProduct newStoreProduct : storeProductList){
            for(StoreProduct suchStoreProduct : suchStoreProducts){
                if(suchStoreProduct.equals(newStoreProduct)){
                    count++;
                }
            }
            if(count == 0){
                storeProductsForCreate.add(newStoreProduct);
            }
            else{
                count = 0;
            }
        }

        if(storeProductsForDelete.size() != 0){
            for(StoreProduct oldStoreProduct : storeProductsForDelete){
                storeProductDao.delete(oldStoreProduct);
            }
        }
        if(storeProductsForCreate.size() != 0){
            for(StoreProduct storeProduct : storeProductsForCreate){
                storeProductDao.create(storeProduct);
            }
        }

        if(oldProduct != null){
            oldProduct = newProduct;
            try{
                productDao.update(oldProduct);
            }
            catch (HibernateException e){
                logger.log(Level.ERROR, "Updating failed." + e.getMessage());
                throw new ServiceException("Updating failed.", e.getCause());
            }
        }
    }



    @Transactional
    public void deleteProduct(Integer id) {
        Product product = productDao.getById(id);
        if(product != null){
            try{
                productDao.delete(product);
            }
            catch (HibernateException e){
                logger.log(Level.ERROR, "Deleting failed." + e.getMessage());
                throw new ServiceException("Deleting failed.", e.getCause());
            }
        }
    }

    public List<ProductDTO> getByNameOrManufacture(String name, String manufacture){

        List<Product> allProducts = productDao.getAll();
        List<Product> sortProducts = new ArrayList<Product>();
        if(name != null && manufacture != null){
            for(Product product : allProducts){
                if(product.getName().equals(name) && product.getManufacture().equals(manufacture)){
                    sortProducts.add(product);
                }
            }
        }
        else if(name != null){
            for(Product product : allProducts){
                if(product.getName().equals(name)){
                    sortProducts.add(product);
                }
            }
        }
        else if(manufacture != null){
            for(Product product : allProducts){
                if(product.getManufacture().equals(manufacture)){
                    sortProducts.add(product);
                }
            }
        }
        List<ProductDTO> dtoProducts = new ArrayList<ProductDTO>();
        ModelMapper modelMapper = new ModelMapper();
        for (Product product : sortProducts) {
            dtoProducts.add(modelMapper.map(product, ProductDTO.class));
        }
        return dtoProducts;
    }

}
