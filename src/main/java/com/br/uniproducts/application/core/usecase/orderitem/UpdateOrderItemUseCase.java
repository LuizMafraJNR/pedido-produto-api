package com.br.uniproducts.application.core.usecase.orderitem;

import com.br.uniproducts.application.core.domain.OrderItem;
import com.br.uniproducts.application.exception.NegocioException;
import com.br.uniproducts.application.ports.in.orderitem.FindOrderItemByIdInputPort;
import com.br.uniproducts.application.ports.in.orderitem.UpdateOrderItemInputPort;
import com.br.uniproducts.application.ports.out.orderitem.UpdateOrderItemOutputPort;

public class UpdateOrderItemUseCase implements UpdateOrderItemInputPort {

    private final UpdateOrderItemOutputPort updateOrderItemOutputPort;
    private final FindOrderItemByIdInputPort findOrderItemByIdInputPort;

    public UpdateOrderItemUseCase(UpdateOrderItemOutputPort updateOrderItemOutputPort, FindOrderItemByIdInputPort findOrderItemByIdInputPort) {
        this.updateOrderItemOutputPort = updateOrderItemOutputPort;
        this.findOrderItemByIdInputPort = findOrderItemByIdInputPort;
    }

    @Override
    public OrderItem update(OrderItem orderItem, Long id) {
        OrderItem existingOrderItem = findOrderItemByIdInputPort.findById(id);
        if (existingOrderItem == null) {
            throw new NegocioException("Order item not found with id: " + id);
        }
        orderItem.setId(id);
        return updateOrderItemOutputPort.update(orderItem);
    }
}
