package com.br.uniproducts.adapters.out.order;

import com.br.uniproducts.adapters.out.repository.OrderRepository;
import com.br.uniproducts.adapters.out.repository.mapper.OrderEntityMapper;
import com.br.uniproducts.application.core.domain.Order;
import com.br.uniproducts.application.ports.out.order.InsertOrderOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertOrderAdapter implements InsertOrderOutputPort
{
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderEntityMapper orderEntityMapper;

	@Override
	public Order insert(Order order)
	{
		var orderEntity = orderRepository.save(orderEntityMapper.toOrderEntity(order));
		return orderEntityMapper.toOrder(orderEntity);
	}
}
