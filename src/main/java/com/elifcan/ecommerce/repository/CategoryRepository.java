package com.elifcan.ecommerce.repository;

import com.elifcan.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Long> {
    Boolean existsByNameAndParentId(String name, Long parentId);

}
