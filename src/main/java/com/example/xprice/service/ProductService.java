package com.example.xprice.service;

import com.example.xprice.model.ProductPriceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final List<PriceScraper> scraperList;

    public List<ProductPriceDTO> getProductPriceById(String id) {
        //Product name should be fetched from the database
        List<ProductPriceDTO> productPrices = new ArrayList<>();
        try {
            for (PriceScraper scraper : scraperList) {
                ProductPriceDTO productPriceDTO = scraper.getProductPrice(id);
                if (productPriceDTO == null) continue;
                productPrices.add(productPriceDTO);
            }
        } catch (Exception e) {
            log.error("Error while fetching product price", e);
        }

        if (productPrices.isEmpty()) return List.of();
        productPrices.sort(Comparator.comparing(ProductPriceDTO::getPrice));
        return productPrices;
    }
}
