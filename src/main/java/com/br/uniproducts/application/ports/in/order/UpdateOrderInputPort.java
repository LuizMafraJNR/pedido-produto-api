package com.br.uniproducts.application.ports.in.order;

import com.br.uniproducts.application.core.domain.Order;

public interface UpdateOrderInputPort {
    Order update(Order order, Long id);
}
