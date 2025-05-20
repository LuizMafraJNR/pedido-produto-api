package com.br.uniproducts.adapters.out.repository;

import com.br.uniproducts.adapters.out.repository.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long>
{
}
