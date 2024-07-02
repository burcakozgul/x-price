package com.example.xprice.controller;

import com.example.xprice.model.ProductPriceDTO;
import com.example.xprice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/price/{id}")
    @Cacheable(value = "productPrices", key = "#id")
    public List<ProductPriceDTO> getProductPriceById(@PathVariable("id") String id) {
        return productService.getProductPriceById(id);
    }
}
