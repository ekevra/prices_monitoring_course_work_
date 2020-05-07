package com.lizavetakeura.controller;

import com.lizavetakeura.api.service.IPriceService;
import com.lizavetakeura.api.service.IProductService;
import com.lizavetakeura.service.CategoryService;
import com.lizavetakeura.service.dto.PriceDTO;
import com.lizavetakeura.service.dto.PriceWithoutDate;
import com.lizavetakeura.service.dto.ProductDTO;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);
    private IProductService productService;
    private IPriceService priceService;

    @Autowired
    public void setPriceService(IPriceService priceService) {
        this.priceService = priceService;
    }
    @Autowired
    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/subcategory/{id}")
    public List<ProductDTO> getProductFromSubcategory(@PathVariable Integer id) {
        return productService.getProductsDTOFromOneSubategory(id);
    }

    @GetMapping("/category/{id}")
    public List<ProductDTO> getProductFromCategory(@PathVariable Integer id) {
        return productService.getProductsDTOFromOneCategory(id);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product) {
        productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/update")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO product) {
        productService.updateProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/newPrice/{id}")
    public void addPrice(@PathVariable Integer id, @RequestBody PriceWithoutDate priceWithoutDate) {

        Date date = new Date();
        try{
            String stringDate = priceWithoutDate.getStart_date();
            date = new SimpleDateFormat("dd-MM-yyyy").parse(stringDate);
        }
        catch(ParseException e){
            logger.log(Level.ERROR, "There is an error with date parsing." + e.getMessage());
            throw new ServiceException("There is an error with date parsing.", e.getCause());
        }
        PriceDTO priceDTO = new PriceDTO(priceWithoutDate.getId(),
                priceWithoutDate.getPrice(),
                date,
                priceWithoutDate.getIsActive());
        priceService.updatePriceOfProduct(id, priceDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
    }

    @GetMapping
    public List<ProductDTO> findProducts(@RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "manufacture", required = false) String manufacture){
        return productService.getByNameOrManufacture(name, manufacture);
    }

    @GetMapping("/prices")
    public List<PriceDTO> getPricesOfProduct(@RequestParam(value = "id", required = true) Integer id,
                                         @RequestParam(value = "firstDate", required = false) String firstDate,
                                         @RequestParam(value = "secondDate", required = false) String secondDate){
        if(firstDate == null && secondDate == null){
            return priceService.getPricesOfProduct(id);
        }
        else{
            return priceService.getPricesOfProduct(id, firstDate, secondDate);
        }
    }
}
