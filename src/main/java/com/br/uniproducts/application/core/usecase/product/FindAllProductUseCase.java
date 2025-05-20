package com.br.uniproducts.application.core.usecase.product;

import com.br.uniproducts.application.core.domain.Product;
import com.br.uniproducts.application.ports.in.product.FindAllProductInputPort;
import com.br.uniproducts.application.ports.out.product.FindAllProductOutputPort;

import java.util.List;

public class FindAllProductUseCase implements FindAllProductInputPort {

    private final FindAllProductOutputPort findAllProductOutputPort;

    public FindAllProductUseCase(FindAllProductOutputPort findAllProductOutputPort) {
        this.findAllProductOutputPort = findAllProductOutputPort;
    }

    @Override
    public List<Product> findAll() {
        return findAllProductOutputPort.findAll();
    }
}
