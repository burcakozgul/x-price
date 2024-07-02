package com.example.xprice.service;


import com.example.xprice.model.ProductPriceDTO;
import com.example.xprice.model.StoreName;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.example.xprice.util.Utils.getPrice;

@Component
@Slf4j
public class HepsiBuradaPriceScraper implements PriceScraper {

    @Override
    public ProductPriceDTO getProductPrice(String id) {
        //productName should be fetched from the database
        if (!id.equals("1")) return null;
        String price = null;
        String productName = null;
        try {
            // The URL is hardcoded here, but it should be in a table
            String url = "https://www.hepsiburada.com/ara?q=macbook+air+m2";
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements priceElements = doc.select("div[data-test-id=price-current-price]");
            if (!priceElements.isEmpty()) {
                price = priceElements.first().text();
            }
            Elements productCards = doc.select("h3[data-test-id=product-card-name]");
            if (!productCards.isEmpty()) {
                productName = productCards.first().text();
            }

        } catch (IOException e) {
            log.error("Error while fetching product price in HepsiBurada", e);
        }

        if (price == null || productName == null) {
            return null;
        }
        return ProductPriceDTO.builder()
                .id(id).name(productName)
                .price(getPrice(price))
                .storeName(getStoreName())
                .lastUpdated(LocalDateTime.now())
                .build();
    }

    @Override
    public StoreName getStoreName() {
        return StoreName.HEPSIBURADA;
    }
}
