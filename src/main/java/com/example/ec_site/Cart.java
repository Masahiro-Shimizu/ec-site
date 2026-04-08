package com.example.ec_site;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// @Entity: kのクラスがDBのcartsテーブルと対応することを示す
@Entity
@Table(name = "carts")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId; // カートID（主キー）
	
	private Integer quantity; // 数量
	
	// ログイン中のユーザーと紐づく
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; // 商品（外部キー）
	
	// カートに入れた商品と紐づく
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product; // 商品（外部キー）

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
