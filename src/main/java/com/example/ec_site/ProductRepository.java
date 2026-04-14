package com.example.ec_site;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//productsテーブルへのCRUD操作を担当
public interface ProductRepository extends JpaRepository<Product, Integer>{

    // 商品名で部分一致検索する
    // LIKE '%keyword%' で検索
	List<Product> findByProductNameContaining(String keyword);
	
	// カテゴリーで検索する
	List<Product> findByCategory(Category category);

	//商品名とカテゴリで検索する
	@Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword% AND p.category = :category")
	List<Product> findByProductNameContainingAndCategory(
			@Param("keyword") String keyword,
			@Param("category") Category category);
}
