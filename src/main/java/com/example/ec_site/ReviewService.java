package com.example.ec_site;

import java.util.List;

import org.springframework.stereotype.Service;

// 口コミに関するビジネスロジックをまとめたサービス
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         UserRepository userRepository,
                         ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // 商品IDで口コミ一覧を取得する
    public List<Review> getReviews(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        return reviewRepository.findByProduct(product);
    }

    // 口コミを投稿する
    public void postReview(String email, Integer productId,
                           Integer rating, String comment,
                           String dummyUserName) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setRating(rating);
        review.setComment(comment);
        review.setDummyUserName(dummyUserName);
        review.setReviewImgPath("");
        reviewRepository.save(review);
    }
}