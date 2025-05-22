package com.br.uniproducts.adapters.out.order;

import com.br.uniproducts.adapters.out.repository.OrderRepository;
import com.br.uniproducts.adapters.out.repository.mapper.OrderEntityMapper;
import com.br.uniproducts.application.core.domain.Order;
import com.br.uniproducts.application.ports.out.order.UpdateOrderOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateOrderAdapter implements UpdateOrderOutputPort
{
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderEntityMapper orderEntityMapper;

	@Override
	public Order update(Order order)
	{
		var orderEntity = orderEntityMapper.toOrderEntity(order);

		return orderEntityMapper.toOrder(orderRepository.save(orderEntity));
	}
}
