package com.example.xprice.service;

import com.example.xprice.model.ProductPriceDTO;
import com.example.xprice.model.StoreName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private PriceScraper scraper1;

    @Mock
    private PriceScraper scraper2;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(Arrays.asList(scraper1, scraper2));
    }

    @Test
    void getProductPriceById() {
        String productId = "1";
        ProductPriceDTO dto1 = new ProductPriceDTO("1", "Product 1", new BigDecimal("100.00"), StoreName.HEPSIBURADA, LocalDateTime.now());
        ProductPriceDTO dto2 = new ProductPriceDTO("1", "Product 1", new BigDecimal("90.00"), StoreName.TRENDYOL, LocalDateTime.now());

        when(scraper1.getProductPrice(productId)).thenReturn(dto1);
        when(scraper2.getProductPrice(productId)).thenReturn(dto2);

        List<ProductPriceDTO> result = productService.getProductPriceById(productId);

        assertEquals(2, result.size());
        assertEquals(dto2, result.get(0));
        assertEquals(dto1, result.get(1));
    }
}