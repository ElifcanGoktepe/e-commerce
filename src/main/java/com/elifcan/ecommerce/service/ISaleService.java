package com.elifcan.ecommerce.service;

import com.elifcan.ecommerce.entity.Product;

import java.util.List;

public interface ISaleService {

    boolean moveToCart(List<Product> productList);
}
