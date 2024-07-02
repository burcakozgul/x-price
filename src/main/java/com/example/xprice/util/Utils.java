package com.example.xprice.util;

import java.math.BigDecimal;

public class Utils {

    public static BigDecimal getPrice(String price) {
        if (price == null) return null;
        return new BigDecimal(price.replace(" TL", "").replace(".","").replace(",","."));
    }
}
