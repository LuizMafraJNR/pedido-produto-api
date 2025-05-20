package com.br.uniproducts.application.ports.in.orderitem;

import com.br.uniproducts.application.core.domain.OrderItem;

public interface UpdateOrderItemInputPort {
    OrderItem update(OrderItem orderItem, Long id);
}
