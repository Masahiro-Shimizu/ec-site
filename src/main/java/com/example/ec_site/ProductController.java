package com.example.ec_site;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 商品に関する画面遷移を担当するコントローラー
@Controller
public class ProductController {

	// ProductRepositoryをコンストラクタインジェクションで受け取る
	private final ProductRepository productRepository;
	
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	// 商品一覧画面の表示
	// DBから全商品を取得してHTMLに渡す
	@GetMapping("/products")
	public String productList(Model model) {
		model.addAttribute("products", productRepository.findAll());
		return "product_list";
	}
	
	// 商品詳細画面の表示
	// URLの{id}部分から商品IDを受け取って該当商品を取得する
	@GetMapping("/products/{id}")
	public String productDetail(@PathVariable Integer id, Model model) {
		Product product = productRepository.findById(id).orElseThrow();
		model.addAttribute("product", product);
		return "product_detail";	
	}
}
