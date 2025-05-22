package com.br.uniproducts.adapters.out.repository.mapper;

import com.br.uniproducts.adapters.out.repository.entity.OrderItemEntity;
import com.br.uniproducts.application.core.domain.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemEntityMapper
{
	OrderItemEntity toOrderItemEntity(OrderItem orderItem);
	OrderItem toOrderItem(OrderItemEntity orderItemEntity);
}
