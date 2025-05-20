package com.br.uniproducts.application.core.usecase.orderitem;

import com.br.uniproducts.application.core.domain.OrderItem;
import com.br.uniproducts.application.ports.in.orderitem.FindOrderItemByIdInputPort;
import com.br.uniproducts.application.ports.out.orderitem.FindOrderItemByIdOutputPort;

public class FindOrderItemByIdUseCase implements FindOrderItemByIdInputPort {

    private final FindOrderItemByIdOutputPort findOrderItemByIdOutputPort;

    public FindOrderItemByIdUseCase(FindOrderItemByIdOutputPort findOrderItemByIdOutputPort) {
        this.findOrderItemByIdOutputPort = findOrderItemByIdOutputPort;
    }

    @Override
    public OrderItem findById(Long id) {
        return findOrderItemByIdOutputPort.findById(id);
    }
}
