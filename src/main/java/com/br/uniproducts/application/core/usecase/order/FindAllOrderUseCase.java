package com.br.uniproducts.application.core.usecase.order;

import com.br.uniproducts.application.core.domain.Order;
import com.br.uniproducts.application.ports.in.order.FindAllOrderInputPort;
import com.br.uniproducts.application.ports.out.order.FindAllOrderOutputPort;

import java.util.List;

public class FindAllOrderUseCase implements FindAllOrderInputPort {

    private final FindAllOrderOutputPort findAllOrderOutputPort;

    public FindAllOrderUseCase(FindAllOrderOutputPort findAllOrderOutputPort) {
        this.findAllOrderOutputPort = findAllOrderOutputPort;
    }

    @Override
    public List<Order> findAll() {
        return findAllOrderOutputPort.findAll();
    }
}
