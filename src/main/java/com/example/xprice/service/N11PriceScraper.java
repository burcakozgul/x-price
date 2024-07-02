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
public class N11PriceScraper implements PriceScraper {

    @Override
    public ProductPriceDTO getProductPrice(String id) {
        //productName should be fetched from the database
        if (!id.equals("1")) return null;
        String price = null;
        String productName = null;
        try {
            // The URL is hardcoded here, but it should be in a table
            String url = "https://www.n11.com/arama?q=macbook+air+m2";
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements priceElements = doc.select("span.newPrice ins");
            if (!priceElements.isEmpty()) {
                price = priceElements.first().text();
            }
            Element productNameElement = doc.selectFirst("h3.productName");
            if (productNameElement != null) {
                productName = productNameElement.text();
            }

        } catch (IOException e) {
            log.error("Error while fetching product price in N11", e);
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
        return StoreName.N11;
    }
}
