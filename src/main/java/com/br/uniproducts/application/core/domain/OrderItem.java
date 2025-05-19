package com.br.uniproducts.application.core.domain;

import java.util.Objects;

public class OrderItem
{
	private long id;
	private Product product;
	private Integer quantity;

	public OrderItem()
	{
	}

	public OrderItem(long id, Product product, Integer quantity)
	{
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}

	public void reduceStock()
	{
		if (hasStock())
		{
			this.product.setStockQuantity(this.product.getStockQuantity() - this.quantity);
		}
		else
		{
			throw new IllegalArgumentException("Insufficient stock for product: " + this.product.getName());
		}
	}

	public boolean hasStock()
	{
		return this.product.getStockQuantity() >= this.quantity;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Product getProduct()
	{
		return product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		OrderItem orderItem = (OrderItem) o;
		return id == orderItem.id && Objects.equals(product, orderItem.product) && Objects.equals(
			quantity, orderItem.quantity);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, product, quantity);
	}
}