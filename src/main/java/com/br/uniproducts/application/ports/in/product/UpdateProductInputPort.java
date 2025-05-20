package com.br.uniproducts.application.ports.in.product;

import com.br.uniproducts.application.core.domain.Product;

public interface UpdateProductInputPort {
    Product update(Product product, Long id);
}
