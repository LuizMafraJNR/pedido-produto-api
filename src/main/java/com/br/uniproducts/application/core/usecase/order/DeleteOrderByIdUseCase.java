package com.br.uniproducts.application.core.usecase.order;

import com.br.uniproducts.application.core.domain.Order;
import com.br.uniproducts.application.exception.NegocioException;
import com.br.uniproducts.application.ports.in.order.DeleteOrderByIdInputPort;
import com.br.uniproducts.application.ports.in.order.FindOrderByIdInputPort;
import com.br.uniproducts.application.ports.out.order.DeleteOrderByIdOutputPort;

public class DeleteOrderByIdUseCase implements DeleteOrderByIdInputPort {

    private final DeleteOrderByIdOutputPort deleteOrderByIdOutputPort;
    private final FindOrderByIdInputPort findOrderByIdInputPort;

    public DeleteOrderByIdUseCase(DeleteOrderByIdOutputPort deleteOrderByIdOutputPort, FindOrderByIdInputPort findOrderByIdInputPort) {
        this.deleteOrderByIdOutputPort = deleteOrderByIdOutputPort;
        this.findOrderByIdInputPort = findOrderByIdInputPort;
    }

    @Override
    public void delete(Long id) {
        Order existintingOrder = findOrderByIdInputPort.findById(id);
        if (existintingOrder != null) {
            deleteOrderByIdOutputPort.delete(id);
        } else {
            throw new NegocioException("Order not found with id: " + id);
        }
    }
}
