package com.br.uniproducts.application.ports.in.orderitem;

import com.br.uniproducts.application.core.domain.OrderItem;

import java.util.List;

public interface FindAllOrderItemInputPort {
    List<OrderItem> findAll();
}
