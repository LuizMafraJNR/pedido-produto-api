package com.br.uniproducts.adapters.out.repository.mapper;

import com.br.uniproducts.adapters.out.repository.entity.OrderEntity;
import com.br.uniproducts.application.core.domain.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper
{
	OrderEntity toOrderEntity(Order order);
	Order toOrder(OrderEntity orderEntity);
}
