package com.br.uniproducts.adapters.out.repository;

import com.br.uniproducts.adapters.out.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>
{
}
