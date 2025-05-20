package com.br.uniproducts.application.ports.in.product;

import com.br.uniproducts.application.core.domain.Product;

import java.util.List;

public interface FindAllProductInputPort {
    List<Product> findAll();
}
