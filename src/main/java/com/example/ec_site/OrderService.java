package com.example.ec_site;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

// 注文に関するビジネスロジックをまとめたサービス
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository,
                        CartRepository cartRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    // 注文確認画面用にカート内容を取得する
    public List<Cart> getCartItems(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return cartRepository.findByUser(user);
    }

    // カートから注文を確定してDBに保存する
    public void placeOrder(String email, String address) {
        User user = userRepository.findByEmail(email).orElseThrow();
        List<Cart> cartItems = cartRepository.findByUser(user);

        int totalAmount = 0;
        for (Cart cart : cartItems) {
            totalAmount += cart.getProduct().getTaxPrice() * cart.getQuantity();
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setStatus("注文確定");

        List<OrderItem> orderItems = new ArrayList<>();
        for (Cart cart : cartItems) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(cart.getProduct());
            item.setQuantity(cart.getQuantity());
            item.setPrice(cart.getProduct().getTaxPrice());
            orderItems.add(item);
        }
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        cartRepository.deleteAll(cartItems);
    }

    // 単品購入: カートを経由せずに直接注文する
    // productId: 購入する商品のID
    // quantity: 購入数量
    public void placeSingleOrder(String email, Integer productId,
                                 Integer quantity, String address) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        // 合計金額を計算
        int totalAmount = product.getTaxPrice() * quantity;

        // Orderオブジェクトを作成
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setStatus("注文確定");

        // OrderItemを作成
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setPrice(product.getTaxPrice());
        orderItems.add(item);

        order.setOrderItems(orderItems);
        orderRepository.save(order);
    }
}