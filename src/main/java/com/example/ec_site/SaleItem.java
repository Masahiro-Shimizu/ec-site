package com.example.ec_site;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Entity: このクラスがDBのsales_itemsテーブルと対応することを示す
@Entity
@Table(name = "sales_items")
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saleItemId;     // セール商品ID（主キー）

    private Integer productId;      // 商品ID
    private Integer companyId;      // 会社ID
    private String saleName;        // セール名
    private String description;     // セール説明
    private Integer discountRate;   // 割引率
    private String salesImgPath;    // セール画像パス
    
 // getter・setter
	public Integer getSaleItemId() {
		return saleItemId;
	}
	public void setSaleItemId(Integer saleItemId) {
		this.saleItemId = saleItemId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}
	public String getSalesImgPath() {
		return salesImgPath;
	}
	public void setSalesImgPath(String salesImgPath) {
		this.salesImgPath = salesImgPath;
	}
}