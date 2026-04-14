package com.example.ec_site;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final ReviewService reviewService;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository,
                             ReviewService reviewService,
                             CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.reviewService = reviewService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/products")
    public String productList(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product_list";
    }

    @GetMapping("/products/search")
    public String searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer categoryId,
            Model model) {
        List<Product> products;
        if (categoryId != null && keyword != null && !keyword.isEmpty()) {
            Category category = categoryRepository.findById(categoryId).orElseThrow();
            products = productRepository.findByProductNameContainingAndCategory(keyword, category);
        } else if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId).orElseThrow();
            products = productRepository.findByCategory(category);
        } else if (keyword != null && !keyword.isEmpty()) {
            products = productRepository.findByProductNameContaining(keyword);
        } else {
            products = productRepository.findAll();
        }
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);
        return "product_list";
    }

    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable Integer id, Model model) {
        Product product = productRepository.findById(id).orElseThrow();
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviewService.getReviews(id));
        return "product_detail";
    }
}