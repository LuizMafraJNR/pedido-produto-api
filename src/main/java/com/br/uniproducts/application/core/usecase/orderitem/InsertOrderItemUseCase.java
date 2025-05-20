package com.br.uniproducts.application.core.usecase.orderitem;

import com.br.uniproducts.application.core.domain.OrderItem;
import com.br.uniproducts.application.ports.in.orderitem.InsertOrderItemInputPort;
import com.br.uniproducts.application.ports.out.orderitem.InsertOrderItemOutputPort;

public class InsertOrderItemUseCase implements InsertOrderItemInputPort {

    private final InsertOrderItemOutputPort insertOrderItemOutputPort;

    public InsertOrderItemUseCase(InsertOrderItemOutputPort insertOrderItemOutputPort) {
        this.insertOrderItemOutputPort = insertOrderItemOutputPort;
    }

    @Override
    public OrderItem insert(OrderItem orderItem) {
        return insertOrderItemOutputPort.insert(orderItem);
    }
}
