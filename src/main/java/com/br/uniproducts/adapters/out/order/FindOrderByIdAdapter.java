package com.br.uniproducts.adapters.out.order;

import com.br.uniproducts.adapters.out.repository.OrderRepository;
import com.br.uniproducts.adapters.out.repository.mapper.OrderEntityMapper;
import com.br.uniproducts.application.core.domain.Order;
import com.br.uniproducts.application.ports.out.order.FindOrderByIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindOrderByIdAdapter implements FindOrderByIdOutputPort
{
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderEntityMapper orderEntityMapper;

	@Override
	public Order findById(Long id)
	{
		var orderEntity = orderRepository.findById(id);
		return orderEntity.map(orderEntityMapper::toOrder).orElse(null);
	}
}
