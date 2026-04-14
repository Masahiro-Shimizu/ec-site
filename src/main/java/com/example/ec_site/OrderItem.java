package com.example.ec_site;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Entity: このクラスがDBのorder_itemsテーブルと対応することを示す
@Entity
@Table(name = "order+items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderItemId; //注文商品ID（主キー）
	
	private Integer quantity; //数量
	private Integer price; //価格
	
	//どの注文に属するか
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	//どの商品か
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public Integer getOrderItemId() {
		return orderItemId;
	}

	//getter/setter
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}	
}
