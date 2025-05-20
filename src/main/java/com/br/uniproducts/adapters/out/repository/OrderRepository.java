package com.br.uniproducts.adapters.out.repository;

import com.br.uniproducts.adapters.out.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>
{
}
