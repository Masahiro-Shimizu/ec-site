package com.example.ec_site;

import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

//@ControllerAdvice: 全てのコントローラーに共通の処理を追加できる
//ここで定義したModelAttributeは全ページで使えるようになる
@ControllerAdvice
public class BaseController {
	
	private final CategoryRepository categoryRepository;
	
	public BaseController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	//全ページでカテゴリ一覧を使えるようにする
	//ヘッダーのカテゴリ選択肢に使用する
	@ModelAttribute("categories")
	public List<Category> categories(){
		return categoryRepository.findAll();
	}
}
