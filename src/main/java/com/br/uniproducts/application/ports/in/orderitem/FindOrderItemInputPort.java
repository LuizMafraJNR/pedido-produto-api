package com.br.uniproducts.application.ports.in.orderitem;

import com.br.uniproducts.application.core.domain.OrderItem;

public interface FindOrderItemInputPort {
    OrderItem findById(Long id);
}
