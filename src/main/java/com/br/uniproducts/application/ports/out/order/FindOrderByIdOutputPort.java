package com.br.uniproducts.application.ports.out.order;

import com.br.uniproducts.application.core.domain.Order;

public interface FindOrderByIdOutputPort {
    Order findById(Long id);
}
