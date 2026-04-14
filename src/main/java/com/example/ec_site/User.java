package com.example.ec_site;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// @Entity: このクラスがDBのusersテーブルと対応することを示す
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    // @NotBlank: 空文字・空白のみを禁止する
    @NotBlank(message = "ユーザー名を入力してください")
    private String userName;

    private String userNameKana;

    // @Email: メールアドレスの形式チェック
    // @NotBlank: 空文字を禁止する
    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "正しいメールアドレスの形式で入力してください")
    private String email;

    private String phone;
    private String userAddress;

    // @Size: 文字数チェック（8文字以上）
    @Size(min = 8, message = "パスワードは8文字以上で入力してください")
    private String passwords;

    // getter・setter
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getUserNameKana() { return userNameKana; }
    public void setUserNameKana(String userNameKana) { this.userNameKana = userNameKana; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getUserAddress() { return userAddress; }
    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }
    public String getPasswords() { return passwords; }
    public void setPasswords(String passwords) { this.passwords = passwords; }
}