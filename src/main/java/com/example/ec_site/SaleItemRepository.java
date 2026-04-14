package com.example.ec_site;

import org.springframework.data.jpa.repository.JpaRepository;

// sale_itemテーブルへのCRUD操作を担当
public interface SaleItemRepository extends JpaRepository<SaleItem, Integer>{

}
