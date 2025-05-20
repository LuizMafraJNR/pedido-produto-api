package com.br.uniproducts.application.core.usecase.order;

import com.br.uniproducts.application.core.domain.Order;
import com.br.uniproducts.application.ports.in.order.InsertOrderInputPort;
import com.br.uniproducts.application.ports.out.order.InsertOrderOutputPort;

public class InsertOrderUseCase implements InsertOrderInputPort {

    private final InsertOrderOutputPort insertOrderOutputPort;

    public InsertOrderUseCase(InsertOrderOutputPort insertOrderOutputPort) {
        this.insertOrderOutputPort = insertOrderOutputPort;
    }

    @Override
    public Order insert(Order order) {
        order.calculateTotal();
        return insertOrderOutputPort.insert(order);
    }
}
