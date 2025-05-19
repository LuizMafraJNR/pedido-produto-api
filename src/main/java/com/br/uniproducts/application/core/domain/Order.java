package com.br.uniproducts.application.core.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Order
{
	private Long id;
	private List<OrderItem> items;
	private BigDecimal total;

	public Order()
	{
	}

	public Order(Long id, List<OrderItem> items, BigDecimal total)
	{
		this.id = id;
		this.items = items;
		this.total = total;
	}

	public BigDecimal calculateTotal()
	{
		return this.items.stream()
			.filter(Objects::nonNull)
			.map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public List<OrderItem> getItems()
	{
		return items;
	}

	public void setItems(List<OrderItem> items)
	{
		this.items = items;
	}

	public BigDecimal getTotal()
	{
		return total;
	}

	public void setTotal(BigDecimal total)
	{
		this.total = total;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		Order order = (Order) o;
		return Objects.equals(id, order.id) && Objects.equals(items, order.items) && Objects.equals(
			total, order.total);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, items, total);
	}
}
