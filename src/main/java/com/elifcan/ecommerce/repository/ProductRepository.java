package com.elifcan.ecommerce.repository;

import com.elifcan.ecommerce.entity.Product;
import com.elifcan.ecommerce.view.VwProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Long> {

    @Query("select new com.elifcan.ecommerce.view.VwProductList(p.id, p.name, p.categoryName, p.image, p.price) from Product p")
    List<VwProductList> getProductList();

    List<Product> findAllByNameContaining(String name);


}
