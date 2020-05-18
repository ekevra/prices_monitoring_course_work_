package com.lizavetakeura.api.service;

import com.lizavetakeura.model.Price;

import java.util.List;

public interface IPriceService<T> {

    public void updateOldPrice(Integer id);

    public void updatePriceOfProduct(Integer productId, T priceDTO);

    public List<Price> getOldPrices(Integer productId);

    public List<T> getPricesOfProduct(Integer productId);

    public List<T> getPricesOfProduct(Integer productId, String firstDate, String lastDate);
}
