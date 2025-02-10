package com.elifcan.ecommerce.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class VwProductList {
    Long id;
    String ad;
    String kategoriAdi;
    String resim;
    BigDecimal fiyat;
}