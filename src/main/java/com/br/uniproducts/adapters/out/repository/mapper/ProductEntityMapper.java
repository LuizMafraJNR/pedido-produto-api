package com.br.uniproducts.adapters.out.repository.mapper;

import com.br.uniproducts.adapters.out.repository.entity.ProductEntity;
import com.br.uniproducts.application.core.domain.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper
{
	ProductEntity toProductEntity(Product product);
	Product toProduct(ProductEntity productEntity);
}
