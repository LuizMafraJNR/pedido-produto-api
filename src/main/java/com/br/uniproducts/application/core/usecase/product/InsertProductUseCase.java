package com.br.uniproducts.application.core.usecase.product;

import com.br.uniproducts.application.core.domain.Product;
import com.br.uniproducts.application.ports.in.product.InsertProductInputPort;
import com.br.uniproducts.application.ports.out.product.InsertProductOutputPort;

public class InsertProductUseCase implements InsertProductInputPort {

    private final InsertProductOutputPort insertProductOutputPort;

    public InsertProductUseCase(InsertProductOutputPort insertProductOutputPort) {
        this.insertProductOutputPort = insertProductOutputPort;
    }

    @Override
    public Product insert(Product product) {
        return insertProductOutputPort.insert(product);
    }
}
