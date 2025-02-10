package com.elifcan.ecommerce.entity;

import com.elifcan.ecommerce.utility.enums.Units;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblproduct")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long categoryId;
    String categoryName;
    String name;
    String image;
    BigDecimal price;
    Integer stock;
    Integer vat;
    Units unit;
    Integer criticalStock;

}
