package com.br.uniproducts.application.core.usecase.product;

import com.br.uniproducts.application.core.domain.Product;
import com.br.uniproducts.application.exception.NegocioException;
import com.br.uniproducts.application.ports.in.product.FindProductByIdInputPort;
import com.br.uniproducts.application.ports.in.product.UpdateProductInputPort;
import com.br.uniproducts.application.ports.out.product.UpdateProductOutputPort;

public class UpdateProductUseCase implements UpdateProductInputPort {

    private final UpdateProductOutputPort updateProductOutputPort;
    private final FindProductByIdInputPort findProductByIdInputPort;

    public UpdateProductUseCase(UpdateProductOutputPort updateProductOutputPort, FindProductByIdInputPort findProductByIdInputPort) {
        this.updateProductOutputPort = updateProductOutputPort;
        this.findProductByIdInputPort = findProductByIdInputPort;
    }

    @Override
    public Product update(Product product, Long id) {
        Product existingProduct = findProductByIdInputPort.findById(id);
        if (existingProduct != null) {
            product.setId(id);
            return updateProductOutputPort.update(product);
        } else {
            throw new NegocioException("Product not found with id: " + id);
        }
    }
}
