package com.example.ec_site;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// 口コミに関するビジネスロジックをまとめたサービス
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    
    // 画像の保存先パス
    private static final String UPLOAD_DIR = "src/main/resources/static/images/revies/";

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
                           String dummyUserName,
                           MultipartFile imgFile) throws IOException {
        User user = userRepository.findByEmail(email).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        //画像ファイルの保存処理
        String imgPath = "";
        if(imgFile != null && !imgFile.isEmpty()) {
        	//保存先フォルダが存在しない場合は作成する
        	Path uploadPath = Paths.get(UPLOAD_DIR);
        	if(!Files.exists(uploadPath)) {
        		Files.createDirectories(uploadPath);
        	}
        	//ファイル名を設定して保存
        	String fileName = System.currentTimeMillis() + "-" + imgFile.getOriginalFilename();
        	Path filePath = uploadPath.resolve(fileName);
        	Files.copy(imgFile.getInputStream(), filePath);
        	imgPath = "/images/reviews/" + fileName; //保存した画像のURLパス
        }
        
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setRating(rating);
        review.setComment(comment);
        review.setDummyUserName(dummyUserName);
        review.setReviewImgPath("");
        review.setCreatedAt(LocalDateTime.now());
        reviewRepository.save(review);
    }
}