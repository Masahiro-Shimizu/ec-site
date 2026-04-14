package com.example.ec_site;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 注文に関する画面遷移を担当するコントローラー
@Controller
public class OrderController{
	
	private final OrderService orderService;
	private final ProductRepository productRepository;
	
	public OrderController(OrderService orderService, ProductRepository productRepository) {
		this.orderService = orderService;
		this.productRepository = productRepository;
	}
	
	// 購入品詳細画面（住所・支払い情報入力）の表示
	@GetMapping("/order")
	public String orderForm(Principal principal, Model model) {
		model.addAttribute("cartItems", orderService.getCartItems(principal.getName()));
		return "order_form";
	}
	
	// 購入確認画面の表示
	@PostMapping("/order/confirm")
	public String orderConfirm(@RequestParam String address, Principal principsl, Model model) {
		model.addAttribute("cartitems", orderService.getCartItems(principsl.getName()));
		model.addAttribute("address", address);
		return "order_confirm";
	}
	
	// 注文確定処理
	@PostMapping("/order/complete")
	public String orderComplete(@RequestParam String address, Principal principal, Model model) {
		orderService.placeOrder(principal.getName(), address);
		model.addAttribute("message", "注文が完了しました");
		return "order_complete";
	}
	
	//単品購入: 商品詳細画面から直接購入品詳細画面へ
	@GetMapping("/order/single")
	public String singleOrderForm(@RequestParam Integer productId, @RequestParam Integer quantity, Model model) {
		Product product = productRepository.findById(productId).orElseThrow();
		model.addAttribute("product", product);
		model.addAttribute("quantity", quantity);
		return "order_single_form";
	}
	
	//単品購入確認画面の表示
	@PostMapping("/order/single/confirm")
	public String singleOrderConfirm(@RequestParam Integer productId, @RequestParam Integer quantity, @RequestParam String address, Model model) {
		Product product = productRepository.findById(productId).orElseThrow();
		model.addAttribute("product", product);
		model.addAttribute("quantity", quantity);
		model.addAttribute("address", address);
		return "order_single_confirm";
	}
	
	//単品購入注文確定処理
	@PostMapping("/order/single/complete")
	public String singleOrderComplete(@RequestParam Integer productId, @RequestParam Integer quantity, @RequestParam String address, Principal principal, Model model) {
		orderService.placeSingleOrder(principal.getName(), productId, quantity, address);
		model.addAttribute("message", "注文が完了しました！");
		return "order_complete";
    }
}
