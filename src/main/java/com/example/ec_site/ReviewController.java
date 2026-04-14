package com.example.ec_site;

import java.io.IOException;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

//口コミに関する画面遷移を担当するコントローラー
@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 口コミ投稿画面の表示
    @GetMapping("/review")
    public String reviewForm(@RequestParam Integer productId, Model model) {
        model.addAttribute("productId", productId);
        return "review_form";
    }

    // 口コミ投稿処理
    @PostMapping("/review")
    public String postReview(@RequestParam Integer productId,
                             @RequestParam Integer rating,
                             @RequestParam String comment,
                             @RequestParam String dummyUserName,
                             @RequestParam(required = false) MultipartFile imgFile,
                             Principal principal) throws IOException{
        reviewService.postReview(
            principal.getName(),
            productId,
            rating,
            comment,
            dummyUserName,
            imgFile
        );
        return "redirect:/products/" + productId;
    }
}