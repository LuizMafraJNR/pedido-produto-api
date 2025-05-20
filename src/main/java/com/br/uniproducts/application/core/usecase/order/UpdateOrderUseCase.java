package com.br.uniproducts.application.core.usecase.order;

import com.br.uniproducts.application.core.domain.Order;
import com.br.uniproducts.application.exception.NegocioException;
import com.br.uniproducts.application.ports.in.order.FindOrderByIdInputPort;
import com.br.uniproducts.application.ports.in.order.UpdateOrderInputPort;
import com.br.uniproducts.application.ports.out.order.UpdateOrderOutputPort;

public class UpdateOrderUseCase implements UpdateOrderInputPort {

    private final UpdateOrderOutputPort updateOrderOutputPort;
    private final FindOrderByIdInputPort findOrderByIdInputPort;

    public UpdateOrderUseCase(UpdateOrderOutputPort updateOrderOutputPort, FindOrderByIdInputPort findOrderByIdInputPort) {
        this.updateOrderOutputPort = updateOrderOutputPort;
        this.findOrderByIdInputPort = findOrderByIdInputPort;
    }

    @Override
    public Order update(Order order, Long id) {
        Order existingOrder = findOrderByIdInputPort.findById(id);
        if (existingOrder != null) {
            order.setId(existingOrder.getId());
            order.calculateTotal();
            return updateOrderOutputPort.update(order);
        } else {
            throw new NegocioException("Order not found with id: " + id);
        }
    }
}
