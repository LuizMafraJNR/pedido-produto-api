package com.br.uniproducts.application.core.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Product
{
	private Long id;
	private String name;
	private BigDecimal price;
	private Integer stockQuantity;

	public Product()
	{
	}

	public Product(Long id, String name, BigDecimal price, Integer stockQuantity)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public Integer getStockQuantity()
	{
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity)
	{
		this.stockQuantity = stockQuantity;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		Product product = (Product) o;
		return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(
			price, product.price) && Objects.equals(stockQuantity, product.stockQuantity);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, name, price, stockQuantity);
	}
}
