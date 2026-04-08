package com.example.ec_site;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// @Entity: このクラスがDBのproductsテーブルと対応することを示す
@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId; // 商品ID（主キー）
	
	private String productName; //商品名
	private Integer price; //価格
	private Integer taxPrice; //税込価格
	private Integer stock; //在庫数
	private String comment; //商品説明
	private String imgPath; //画像パス
	private Integer includeTax; //税率
	
	// @ManyToOne: 多対一のリレーション（多くの商品が1つの会社に属する）
	// ＠JoinColumn: 外部キーのカラム名を指定
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company; //会社（外部キー）
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category; //カテゴリ（外部キー）

	// getter・setter
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(Integer taxPrice) {
		this.taxPrice = taxPrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getIncludeTax() {
		return includeTax;
	}

	public void setIncludeTax(Integer includeTax) {
		this.includeTax = includeTax;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
