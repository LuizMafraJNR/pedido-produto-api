package com.br.uniproducts.application.core.usecase.orderitem;

import com.br.uniproducts.application.core.domain.OrderItem;
import com.br.uniproducts.application.ports.in.orderitem.FindAllOrderItemInputPort;
import com.br.uniproducts.application.ports.out.orderitem.FindAllOrderItemOutputPort;

import java.util.List;

public class FindAllOrderItemUseCase implements FindAllOrderItemInputPort {

    private final FindAllOrderItemOutputPort findAllOrderItemOutputPort;

    public FindAllOrderItemUseCase(FindAllOrderItemOutputPort findAllOrderItemOutputPort) {
        this.findAllOrderItemOutputPort = findAllOrderItemOutputPort;
    }

    @Override
    public List<OrderItem> findAll() {
        return findAllOrderItemOutputPort.findAll();
    }
}
