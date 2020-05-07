package com.lizavetakeura.service;

import com.lizavetakeura.api.dao.IOldPriceDao;
import com.lizavetakeura.api.dao.IPriceDao;
import com.lizavetakeura.api.dao.IProductDao;
import com.lizavetakeura.api.service.IPriceService;
import com.lizavetakeura.model.helpers.OldPrice;
import com.lizavetakeura.model.Price;
import com.lizavetakeura.model.Product;
import com.lizavetakeura.service.dto.PriceDTO;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PriceService implements IPriceService<PriceDTO> {

    private static final Logger logger = LogManager.getLogger(ProductService.class);
    private IOldPriceDao oldPriceDao;
    private IPriceDao priceDao;
    private IProductDao productDao;

    public PriceService(){
    }

    @Autowired
    public void setOldPriceDao(IOldPriceDao oldPriceDao) {
        this.oldPriceDao = oldPriceDao;
    }

    @Autowired
    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }

    @Autowired
    public void setPriceDao(IPriceDao priceDao) {
        this.priceDao = priceDao;
    }

    public void updateOldPrice(Integer id){
        Price oldPrice = priceDao.getById(id);
        if(oldPrice != null){
            oldPrice.setIsActive(0);
            priceDao.update(oldPrice);
        }
    }

    @Transactional
    public void updatePriceOfProduct(Integer productId, PriceDTO priceDTO){
        ModelMapper modelMapper = new ModelMapper();
        Price newPrice = modelMapper.map(priceDTO, Price.class);
        Product product = productDao.getById(productId);
        updateOldPrice(product.getPrice().getId());
        OldPrice oldPrice = new OldPrice(product.getPrice().getId(), productId);
        oldPriceDao.create(oldPrice);
        product.setPrice(newPrice);
        priceDao.create(newPrice);
        productDao.update(product);
    }

    public List<Price> getOldPrices(Integer productId){

        List<Price> allPrices = new ArrayList<Price>();
        List<OldPrice> oldPrices = oldPriceDao.getPricesByProductId(productId);
        if(oldPrices.size() > 0){
            for(OldPrice oldPrice : oldPrices){
                allPrices.add(priceDao.getById(oldPrice.getPrice_id()));
            }
        }
        return allPrices;
    }

    public List<PriceDTO> getPricesOfProduct(Integer productId){
        List<Price> prices = getAllPricesOfProduct(productId);
        return fromEntityToDTO(prices);
    }

    public List<PriceDTO> getPricesOfProduct(Integer productId, String firstDate, String lastDate){

        Date startDate = new Date();
        Date finishDate = new Date();
        try{
            startDate = new SimpleDateFormat("dd-MM-yyyy").parse(firstDate);
            finishDate = new SimpleDateFormat("dd-MM-yyyy").parse(lastDate);
        }
        catch(ParseException e){
            logger.log(Level.ERROR, "There is an error with date parsing." + e.getMessage());
            throw new ServiceException("There is an error with date parsing.", e.getCause());
        }

        List<Price> needPrices = new ArrayList<Price>();
        List<Price> allPrices = getAllPricesOfProduct(productId);
        Collections.sort(allPrices);

        for(Price price : allPrices){
            if(price.getStart_date().after(startDate) && price.getStart_date().before(finishDate)){
                if(needPrices.size() == 0){
                    Integer index = allPrices.indexOf(price);
                    if(index != 0){
                        needPrices.add(allPrices.get(index-1));
                    }
                }
                needPrices.add(price);
            }
        }
        return fromEntityToDTO(needPrices);
    }

    public List<Price> getAllPricesOfProduct(Integer productId){
        List<Price> prices = getOldPrices(productId);
        prices.add(productDao.getById(productId).getPrice());
        return prices;
    }

    private List<PriceDTO> fromEntityToDTO(List<Price> prices){
        List<PriceDTO> pricesDTO = new ArrayList<PriceDTO>();
        ModelMapper modelMapper = new ModelMapper();
        for(Price price : prices){
            pricesDTO.add(modelMapper.map(price, PriceDTO.class));
        }
        return pricesDTO;
    }
}
