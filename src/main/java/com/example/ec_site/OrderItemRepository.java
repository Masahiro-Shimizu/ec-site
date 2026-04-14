package com.example.ec_site;

import org.springframework.data.jpa.repository.JpaRepository;

//order_itemsテーブルへのCRUD操作を担当
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
