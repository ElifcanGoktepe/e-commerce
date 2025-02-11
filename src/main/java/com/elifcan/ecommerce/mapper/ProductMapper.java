package com.elifcan.ecommerce.mapper;

import com.elifcan.ecommerce.dto.request.AddProductRequestDto;
import com.elifcan.ecommerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Mapstruct -> provides information transfer between two different java class with their objects
 * Be careful to props
 * componentModel -> feature of adjustment which IMPL class need to be created. For this
 * application IMPL is spring
 *
 * Product from AddProductRequestDto(dto); in this structure, objects in the dto and entity class
 * can not match everytime. Some objects can be avoided. These objects should be explained. On the
 * other hand, error occurs.
 */

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    /**
     * We need a mapper class for this interface, therefore we write the code below to be executed.
     */

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    /**
     * Create methods for requested objects to be transferred.
     */

    Product createProductObjectFromDto(AddProductRequestDto dto);

}
