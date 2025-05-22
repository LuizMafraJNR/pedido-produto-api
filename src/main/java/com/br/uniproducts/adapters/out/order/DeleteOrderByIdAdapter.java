package com.br.uniproducts.adapters.out.order;

import com.br.uniproducts.adapters.out.repository.OrderRepository;
import com.br.uniproducts.application.ports.out.order.DeleteOrderByIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteOrderByIdAdapter implements DeleteOrderByIdOutputPort
{
	@Autowired
	private  OrderRepository orderRepository;

	@Override
	public void delete(Long id)
	{
		orderRepository.deleteById(id);
	}
}
