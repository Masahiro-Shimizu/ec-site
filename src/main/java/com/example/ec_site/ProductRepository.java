package com.example.ec_site;

import org.springframework.data.jpa.repository.JpaRepository;

// productsテーブルへのCRUD操作を担当
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
