package com.br.uniproducts.application.ports.in.order;

import com.br.uniproducts.application.core.domain.Order;

public interface InsertOrderInputPort {
    Order insert(Order order);
}
