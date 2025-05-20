package com.br.uniproducts.application.core.usecase.product;

import com.br.uniproducts.application.core.domain.Product;
import com.br.uniproducts.application.exception.NegocioException;
import com.br.uniproducts.application.ports.in.product.DeleteProductByIdInputPort;
import com.br.uniproducts.application.ports.in.product.FindProductByIdInputPort;
import com.br.uniproducts.application.ports.out.product.DeleteProductByIdOutputPort;

public class DeleteProductByIdUseCase implements DeleteProductByIdInputPort {

    private final DeleteProductByIdOutputPort deleteProductByIdOutputPort;
    private final FindProductByIdInputPort findProductByIdInputPort;

    public DeleteProductByIdUseCase(DeleteProductByIdOutputPort deleteProductByIdOutputPort, FindProductByIdInputPort findProductByIdInputPort) {
        this.deleteProductByIdOutputPort = deleteProductByIdOutputPort;
        this.findProductByIdInputPort = findProductByIdInputPort;
    }
    @Override
    public void delete(Long id) {
        Product existingProduct = findProductByIdInputPort.findById(id);
        if (existingProduct != null) {
            deleteProductByIdOutputPort.delete(id);
        } else {
            throw new NegocioException("Product not found with id: " + id);
        }
    }
}
