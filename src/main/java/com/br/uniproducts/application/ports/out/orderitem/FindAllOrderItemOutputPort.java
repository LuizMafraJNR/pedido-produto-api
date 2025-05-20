package com.br.uniproducts.application.ports.out.orderitem;

import com.br.uniproducts.application.core.domain.OrderItem;

import java.util.List;

public interface FindAllOrderItemOutputPort {
    List<OrderItem> findAll();
}
