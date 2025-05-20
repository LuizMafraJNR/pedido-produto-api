package com.br.uniproducts.application.core.usecase.orderitem;

import com.br.uniproducts.application.core.domain.OrderItem;
import com.br.uniproducts.application.exception.NegocioException;
import com.br.uniproducts.application.ports.in.orderitem.DeleteOrderItemByIdInputPort;
import com.br.uniproducts.application.ports.in.orderitem.FindOrderItemByIdInputPort;
import com.br.uniproducts.application.ports.out.orderitem.DeleteOrderItemByIdOutputPort;

public class DeleteOrderItemByIdUseCase implements DeleteOrderItemByIdInputPort {

    private final FindOrderItemByIdInputPort findOrderItemInputPort;
    private final DeleteOrderItemByIdOutputPort deleteOrderItemByIdOutputPort;

    public DeleteOrderItemByIdUseCase(FindOrderItemByIdInputPort findOrderItemInputPort, DeleteOrderItemByIdOutputPort deleteOrderItemByIdOutputPort) {
        this.findOrderItemInputPort = findOrderItemInputPort;
        this.deleteOrderItemByIdOutputPort = deleteOrderItemByIdOutputPort;
    }

    @Override
    public void delete(Long id) {
        OrderItem existingOrderItem = findOrderItemInputPort.findById(id);
        if (existingOrderItem != null) {
            deleteOrderItemByIdOutputPort.delete(id);
        } else {
            throw new NegocioException("Order item not found with id: " + id);
        }
    }
}
