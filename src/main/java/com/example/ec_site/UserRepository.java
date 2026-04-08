package com.example.ec_site;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<User, Long>を継承するだけで
//findAll・save・deleteByIdなどのCRUDメソッドが自動で使えるようになる
//第1引数: 操作対象のエンティティクラス（User）
//第2引数: 主キーの型（Long）
public interface UserRepository extends JpaRepository<User, Long>{

	// メールアドレスでユーザーを検索するメソッド
    // メソッド名の命名規則によってSQLが自動生成される
    // → SELECT * FROM users WHERE email = ?
    // Optionalで返すことでnullを安全に扱える
	Optional<User> findByEmail(String email);
}
