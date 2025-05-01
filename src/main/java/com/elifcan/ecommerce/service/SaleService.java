package com.elifcan.ecommerce.service;
import com.elifcan.ecommerce.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SaleService implements ISaleService{
    @Override
    public boolean moveToCart(List<Product> prodcutList) {
        return false;
    }
}