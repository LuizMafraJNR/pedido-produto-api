package com.br.uniproducts.application.ports.in.order;

import com.br.uniproducts.application.core.domain.Order;

import java.util.List;

public interface FindAllOrderInputPort {
    List<Order> findAll();
}
