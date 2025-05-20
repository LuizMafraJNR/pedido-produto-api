package com.br.uniproducts.application.ports.in.product;

import com.br.uniproducts.application.core.domain.Product;

public interface InsertProductInputPort {
    Product insert(Product product);
}
