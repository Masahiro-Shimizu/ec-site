package com.example.ec_site;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// cartstーブルへのCRUD操作を担当
public interface CartRepository extends JpaRepository<Cart, Long>{
	
	// ユーザーIDでカート一覧を取得する
	// → SELECT * FROM carts WHERE user_id = ?
	List<Cart> findByUser(User user);
}
