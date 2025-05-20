package com.br.uniproducts.application.core.usecase.order;

import com.br.uniproducts.application.core.domain.Order;
import com.br.uniproducts.application.ports.in.order.FindOrderByIdInputPort;
import com.br.uniproducts.application.ports.out.order.FindOrderByIdOutputPort;

public class FindOrderByIdUseCase implements FindOrderByIdInputPort {

    private final FindOrderByIdOutputPort findOrderByIdOutputPort;

    public FindOrderByIdUseCase(FindOrderByIdOutputPort findOrderByIdOutputPort) {
        this.findOrderByIdOutputPort = findOrderByIdOutputPort;
    }

    @Override
    public Order findById(Long id) {
        return findOrderByIdOutputPort.findById(id);
    }
}
