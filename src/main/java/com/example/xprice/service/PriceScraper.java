package com.example.xprice.service;

import com.example.xprice.model.ProductPriceDTO;
import com.example.xprice.model.StoreName;

public interface PriceScraper {
    ProductPriceDTO getProductPrice(String id);
    StoreName getStoreName();
}
