package com.br.uniproducts.application.ports.in.orderitem;

import com.br.uniproducts.application.core.domain.OrderItem;

public interface FindOrderItemByIdInputPort {
    OrderItem findById(Long id);
}
