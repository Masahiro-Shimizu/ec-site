package com.example.ec_site;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
// カートに関する画面推移を担当するコントローラー
public class CartController {
	
	private final CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	// カートに商品を追加する処理
	// Principal: ログイン中のユーザー情報を取得するSpringの仕組み
	@PostMapping("/cart/add")
	public String addToCart(@RequestParam Integer productId, @RequestParam Integer quantity, Principal principal) {
		// principal.getName()でログイン中のメールアドレスを取得
		cartService.addToCart(principal.getName(), productId, quantity);
		return "redirect:/cart/added";
	}
	
	// カート追加確認画面の表示
	@GetMapping("/cart/added")
	public String cartAdded(Principal principal, Model model) {
		model.addAttribute("cartItems", cartService.getCartItems(principal.getName()));
		return "cart_added";
	}
	
	// カート詳細画面の表示
	@GetMapping("/cart")
	public String cartDetail(Principal principal, Model model) {
		model.addAttribute("cartItems", cartService.getCartItems(principal.getName()));
		return "cart_detail";
	}
	
	// カートから商品を削除する処理
	@PostMapping("/cart/remove")
	public String removeFromCart(@RequestParam Long cartId) {
		cartService.removeFromCart(cartId);
		return "redirect:/cart";
	}
}
