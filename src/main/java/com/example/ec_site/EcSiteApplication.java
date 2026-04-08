package com.example.ec_site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication: Spring Bootアプリケーションの起点であることを示す
//以下の3つのアノテーションをまとめたもの
//@Configuration: このクラスがBean定義の設定クラスであることを示す
//@EnableAutoConfiguration: Spring Bootの自動設定を有効にする
//@ComponentScan: このパッケージ配下のコンポーネントを自動で検出する
@SpringBootApplication
public class EcSiteApplication {

	// アプリケーションの起動メソッド
    // ここからSpring Bootが起動する
	public static void main(String[] args) {
		SpringApplication.run(EcSiteApplication.class, args);
	}

}
