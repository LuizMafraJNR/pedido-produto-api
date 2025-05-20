package com.br.uniproducts.application.ports.out.orderitem;

import com.br.uniproducts.application.core.domain.OrderItem;

public interface InsertOrderItemOutputPort {
    OrderItem insert(OrderItem orderItem);
}
