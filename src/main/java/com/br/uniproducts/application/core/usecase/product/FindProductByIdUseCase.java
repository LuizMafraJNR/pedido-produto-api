package com.br.uniproducts.application.core.usecase.product;

import com.br.uniproducts.application.core.domain.Product;
import com.br.uniproducts.application.ports.in.product.FindProductByIdInputPort;
import com.br.uniproducts.application.ports.out.product.FindProductByIdOutputPort;

public class FindProductByIdUseCase implements FindProductByIdInputPort {

    private final FindProductByIdOutputPort findProductByIdOutputPort;

    public FindProductByIdUseCase(FindProductByIdOutputPort findProductByIdOutputPort) {
        this.findProductByIdOutputPort = findProductByIdOutputPort;
    }
    @Override
    public Product findById(Long id) {
        return findProductByIdOutputPort.findById(id);
    }
}
