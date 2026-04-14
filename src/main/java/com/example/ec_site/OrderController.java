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
	
	// カートからの購入品詳細画面の表示
    @GetMapping("/order")
    public String orderForm(Principal principal, Model model) {
        model.addAttribute("cartItems", orderService.getCartItems(principal.getName()));
        return "order_form";
    }
	
 // カートからの購入確認画面の表示
    @PostMapping("/order/confirm")
    public String orderConfirm(
            @RequestParam(required = false, defaultValue = "") String recipientName,
            @RequestParam(required = false, defaultValue = "") String address,
            @RequestParam(required = false, defaultValue = "") String apartmentName,
            @RequestParam(required = false, defaultValue = "未選択") String paymentMethod,
            Principal principal,
            Model model) {
        model.addAttribute("cartItems", orderService.getCartItems(principal.getName()));
        model.addAttribute("recipientName", recipientName);
        model.addAttribute("address", address);
        model.addAttribute("apartmentName", apartmentName);
        model.addAttribute("paymentMethod", paymentMethod);
        return "order_confirm";
    }
	
 // カートからの注文確定処理
    @PostMapping("/order/complete")
    public String orderComplete(
            @RequestParam(required = false, defaultValue = "") String recipientName,
            @RequestParam(required = false, defaultValue = "") String address,
            @RequestParam(required = false, defaultValue = "") String apartmentName,
            @RequestParam(required = false, defaultValue = "未選択") String paymentMethod,
            Principal principal,
            Model model) {
    	//注文前にカート情報を取得しておく
    	java.util.List<Cart> cartItems = orderService.getCartItems(principal.getName());
    	int totalAmount = cartItems.stream()
    			.mapToInt(c -> c.getProduct().getTaxPrice() * c.getQuantity())
    			.sum();
    	model.addAttribute("cartItems", cartItems);
    	model.addAttribute("totalAmount", totalAmount);
    	model.addAttribute("recipientName", recipientName);
    	model.addAttribute("address", address);
    	model.addAttribute("apartmentName", apartmentName);
    	model.addAttribute("paymentMethod", paymentMethod);
        orderService.placeOrder(principal.getName(), recipientName,
                                address, apartmentName, paymentMethod);
        model.addAttribute("message", "注文が完了しました！");
        return "order_complete";
    }
	
 // 単品購入: 商品詳細画面から直接購入品詳細画面へ
    @GetMapping("/order/single")
    public String singleOrderForm(@RequestParam Integer productId,
                                  @RequestParam Integer quantity,
                                  Model model) {
        Product product = productRepository.findById(productId).orElseThrow();
        model.addAttribute("product", product);
        model.addAttribute("quantity", quantity);
        return "order_single_form";
    }
	
 // 単品購入確認画面の表示
    @PostMapping("/order/single/confirm")
    public String singleOrderConfirm(
            @RequestParam Integer productId,
            @RequestParam Integer quantity,
            @RequestParam(required = false, defaultValue = "") String recipientName,
            @RequestParam(required = false, defaultValue = "") String address,
            @RequestParam(required = false, defaultValue = "") String apartmentName,
            @RequestParam(required = false, defaultValue = "未選択") String paymentMethod,
            Model model) {
        Product product = productRepository.findById(productId).orElseThrow();
        model.addAttribute("product", product);
        model.addAttribute("quantity", quantity);
        model.addAttribute("recipientName", recipientName);
        model.addAttribute("address", address);
        model.addAttribute("apartmentName", apartmentName);
        model.addAttribute("paymentMethod", paymentMethod);
        return "order_single_confirm";
    }
	
 // 単品購入注文確定処理
    @PostMapping("/order/single/complete")
    public String singleOrderComplete(
            @RequestParam Integer productId,
            @RequestParam Integer quantity,
            @RequestParam(required = false, defaultValue = "") String recipientName,
            @RequestParam(required = false, defaultValue = "") String address,
            @RequestParam(required = false, defaultValue = "") String apartmentName,
            @RequestParam(required = false, defaultValue = "未選択") String paymentMethod,
            Principal principal,
            Model model) {
        Product product = productRepository.findById(productId).orElseThrow();
        int totalAmount = product.getTaxPrice() * quantity;
        model.addAttribute("product", product);
        model.addAttribute("quantity", quantity);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("recipientName", recipientName);
        model.addAttribute("address", address);
        model.addAttribute("apartmentName", apartmentName);
        model.addAttribute("paymentMethod", paymentMethod);
        orderService.placeSingleOrder(principal.getName(), productId, quantity,
                                      recipientName, address, apartmentName, paymentMethod);
        return "order_complete";
    }
}
