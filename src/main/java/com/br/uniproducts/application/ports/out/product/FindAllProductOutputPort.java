package com.br.uniproducts.application.ports.out.product;

import com.br.uniproducts.application.core.domain.Product;

import java.util.List;

public interface FindAllProductOutputPort {
    List<Product> findAll();
}
