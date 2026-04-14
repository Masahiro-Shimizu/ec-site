package com.example.ec_site;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
// カートに関するビジネスロジックをまとめたサービス
public class CartService {
	
	private final CartRepository cartRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	
	public CartService(CartRepository cartRepository,UserRepository userRepository,ProductRepository productRepository) {
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}
	
	// カートに商品を追加する
	// email: ログイン中のユーザーのメールアドレス
	// productId: カートに追加する商品のID
	// quantity: 数量
	public void addToCart(String email, Integer productId, Integer quantity) {
		
		// メールアドレスからユーザーを取得
		User user = userRepository.findByEmail(email).orElseThrow();
		
		// 商品IDから商品を取得
		Product product = productRepository.findById(productId).orElseThrow();
		
		// Cartオブジェクトを作成してDBに保存
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setProduct(product);
		cart.setQuantity(quantity);
		cartRepository.save(cart);
	}
	
	// ログイン中のユーザーのカート一覧を取得する
	public List<Cart> getCartItems(String email){
		User user = userRepository.findByEmail(email).orElseThrow();
		return cartRepository.findByUser(user);
	}
	
	// カートから商品を削除する
	public void removeFromCart(Long cartId) {
		cartRepository.deleteById(cartId);
	}

	public void updateQuantity(Long cartId, Integer quantity) {
		Cart cart = cartRepository.findById(cartId).orElseThrow();
		cart.setQuantity(quantity);
		cartRepository.save(cart);
	}
}
