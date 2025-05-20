package com.br.uniproducts.application.ports.out.orderitem;

import com.br.uniproducts.application.core.domain.OrderItem;

public interface FindOrderItemByIdOutputPort {
    OrderItem findById(Long id);
}
