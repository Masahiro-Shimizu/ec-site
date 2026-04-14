package com.example.ec_site;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SaleItemRepository saleItemRepository;

    public AuthController(UserService userService,
                          OrderRepository orderRepository,
                          UserRepository userRepository,
                          ProductRepository productRepository,
                          SaleItemRepository saleItemRepository) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.saleItemRepository = saleItemRepository;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "メールアドレスまたはパスワードが違います");
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid User user,
                           BindingResult bindingResult,
                           @RequestParam String password,
                           @RequestParam String passwordConfirm,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (!password.equals(passwordConfirm)) {
            model.addAttribute("errorMessage", "パスワードが一致しません");
            return "register";
        }
        if (password.length() < 8) {
            model.addAttribute("errorMessage", "パスワードは8文字以上で入力してください");
            return "register";
        }
        userService.register(user, password);
        return "redirect:/login";
    }

 // TOP画面の表示
    @GetMapping("/top")
    public String topPage(Model model) {
        List<SaleItem> saleItems = saleItemRepository.findAll();

        // SaleItemをSaleItemDtoに変換する
        // 各セール品に対応する商品情報を取得して割引価格を計算する
        List<SaleItemDto> salesItemDtos = new ArrayList<>();
        for (SaleItem saleItem : saleItems) {
            Product product = productRepository.findById(saleItem.getProductId()).orElseThrow();
            salesItemDtos.add(new SaleItemDto(saleItem, product));
        }

        model.addAttribute("salesItems", salesItemDtos);
        return "top";
    }

    // マイページの表示
    @GetMapping("/mypage")
    public String myPage(Principal principal, Model model) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("orders", orderRepository.findByUser(user));
        return "mypage";
    }
}