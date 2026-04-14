package com.example.ec_site;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// ordersテーブルへのCRUD操作を担当
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // ユーザーの注文一覧を取得する
    // → SELECT * FROM orders WHERE user_id = ?
    List<Order> findByUser(User user);
}
