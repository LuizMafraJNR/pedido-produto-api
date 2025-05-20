package com.br.uniproducts.application.ports.out.product;

import com.br.uniproducts.application.core.domain.Product;

public interface UpdateProductOutputPort {
    Product update(Product product);
}
