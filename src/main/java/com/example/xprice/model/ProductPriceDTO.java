package com.example.xprice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPriceDTO {
    private String id;
    private String name;
    private BigDecimal price;
    private StoreName storeName;
    private LocalDateTime lastUpdated;
}
