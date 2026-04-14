package com.example.ec_site;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

	private final CartService cartService;
	private final CategoryRepository categoryRepository;

	public CartController(CartService cartService, CategoryRepository categoryRepository) {
		this.cartService = cartService;
		this.categoryRepository = categoryRepository;
	}

	@PostMapping("/cart/add")
	public String addToCart(@RequestParam Integer productId, @RequestParam Integer quantity, Principal principal) {
		cartService.addToCart(principal.getName(), productId, quantity);
		return "redirect:/cart/added";
	}

	@GetMapping("/cart/added")
	public String cartAdded(Principal principal, Model model) {
		List<Cart> cartItems = cartService.getCartItems(principal.getName());
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("message", "カートに追加しました");
		return "cart_added";
	}

	@GetMapping("/cart")
	public String cartDetail(Principal principal, Model model) {
		List<Cart> cartItems = cartService.getCartItems(principal.getName());
		int totalPrice = 0;
		for (Cart item : cartItems) {
			totalPrice += item.getProduct().getTaxPrice() * item.getQuantity();
		}
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("categories", categoryRepository.findAll());
		return "cart_detail";
	}

	@PostMapping("/cart/remove")
	public String removeFromCart(@RequestParam Long cartId) {
		cartService.removeFromCart(cartId);
		return "redirect:/cart";
	}

	@PostMapping("/cart/update")
	public String updateQuantity(@RequestParam Long cartId, @RequestParam Integer quantity) {
		cartService.updateQuantity(cartId, quantity);
		return "redirect:/cart";
	}
}
