package com.example.ec_site;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration: このクラスがSpringの設定クラスであることを示す
//Spring Securityの各種設定をここにまとめる
@Configuration
public class SecurityConfig {

	// @Bean: このメソッドの戻り値をSpringのコンテナに登録する
    // パスワードの暗号化方式としてBCryptを使用する
    // BCrypt: ハッシュ化アルゴリズムの一種で、パスワード保存に最適
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Spring Securityのアクセス制御・ログイン・ログアウトの設定
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		// URLごとのアクセス制御
		http.authorizeHttpRequests(auth -> auth
		     // /register と /login はログインなしでアクセス可能
			.requestMatchers("/register", "/login", "/logout", "/css/**", "/js/**", "/images/**").permitAll()
			// 上記以外のURLはログイン必須
			.anyRequest().authenticated()
			)
		// ログインの設定
		.formLogin(form -> form
			// ログインページのURL
			.loginPage("/login")
			// ログイン処理のURL（POSTを受け取るURL）
			.loginProcessingUrl("/login")
			// ログインIDとしてemailを使用する
			.usernameParameter("email")
			// ログイン成功後に遷移するURL
			.defaultSuccessUrl("/top", true)
			// ログイン失敗時に遷移するURL
			.failureUrl("/login?error")
			.permitAll()
			)
		// ログアウトの設定
		.logout(logout -> logout
			// ログアウト後に遷移するURL
			.logoutSuccessUrl("/login?logout")
			.permitAll()
			);
		
		return http.build();
	}
}
