package com.br.uniproducts.adapters.out.order;

import com.br.uniproducts.adapters.out.repository.OrderRepository;
import com.br.uniproducts.adapters.out.repository.mapper.OrderEntityMapper;
import com.br.uniproducts.application.core.domain.Order;
import com.br.uniproducts.application.ports.out.order.FindAllOrderOutputPort;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindAllOrderAdapter implements FindAllOrderOutputPort
{
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderEntityMapper orderEntityMapper;

	@Override
	public List<Order> findAll()
	{
		var orderEntityList = orderRepository.findAll();
		var orderList = new ArrayList<Order>();
		for (var orderEntity : orderEntityList)
		{
			orderList.add(orderEntityMapper.toOrder(orderEntity));
		}
		return orderList;
	}
}
