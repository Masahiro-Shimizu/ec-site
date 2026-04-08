package com.example.ec_site;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Entity: このクラスがDBのテーブルと対応することを示す
//JPAがこのクラスをもとにSQLを自動生成する
@Entity
//@Table: 対応するテーブル名を指定する
//指定しない場合はクラス名がテーブル名になる
//"user"はMySQLの予約語のため"users"を指定
@Table(name = "users")
public class User {

	// @Id: このフィールドが主キーであることを示す
	@Id
	// @GeneratedValue: 主キーの値を自動生成する
    // IDENTITY: DBのAUTO_INCREMENTを使用する
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId; // ユーザーID（主キー）
	
	private String userName; // ユーザー名
	private String userNameKana; // ユーザー名（かな）
	private String email; // メールアドレス（ログインID）
	private String phone; // 電話番号
	private String userAddress; // 住所
	private String passwords; // パスワード（暗号化して保存）
	
	// getter・setter
    // Springがフィールドの値を取得・設定するために必要
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNameKana() {
		return userNameKana;
	}
	public void setUserNameKana(String userNameKana) {
		this.userNameKana = userNameKana;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getPasswords() {
		return passwords;
	}
	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}
}
