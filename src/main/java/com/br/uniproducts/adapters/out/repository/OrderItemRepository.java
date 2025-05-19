package com.br.uniproducts.adapters.out.repository;

import com.br.uniproducts.adapters.out.repository.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long>
{
}
