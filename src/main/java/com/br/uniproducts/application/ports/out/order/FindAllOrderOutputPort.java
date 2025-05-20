package com.br.uniproducts.application.ports.out.order;

import com.br.uniproducts.application.core.domain.Order;

import java.util.List;

public interface FindAllOrderOutputPort {
    List<Order> findAll();
}
