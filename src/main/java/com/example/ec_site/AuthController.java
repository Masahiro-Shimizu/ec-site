package com.example.ec_site;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @Controller: このクラスがコントローラー層であることをSprongに伝える
// URLのルーティングと画面遷移を担当する
@Controller
public class AuthController {

	// UserServiceをコンストラクタインジェクションで受け取る
	// Springが自動でUserServiceのインスタンスを渡してくれる
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ログイン画面の表示
    // GETリクエスト: URLにアクセスされたとき画面を表示するだけ
    // @RequestParam(required = false): errorパラメータは任意(なくてもOK)
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
    	// ログイン失敗時にURLに?errorが付くのでエラーメッセージを渡す
        if (error != null) {
            model.addAttribute("errorMessage", "メールアドレスまたはパスワードが違います");
        }
        return "login";
    }

    // 新規画面登録の表示
    // 空のUserオブジェクトをModelに渡してThymeleafのth:objectで使えるようにする
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // 新規登録処理
    // POSTリクエスト: フォームからデータを受け取って登録処理を行う
    // User user: th:objectで紐づいたフォームの値が自動でセットされる
    // @RequestParam String password: name="password"の入力値を受け取る
    // @RequestParam String passwordConfirm: name="passwordConfirm"の入力値を受け取る
    @PostMapping("/register")
    public String register(User user, @RequestParam String password, @RequestParam String passwordConfirm, Model model) {
        // パスワードと確認用のパスワードが一致するか確認
    	if (!password.equals(passwordConfirm)) {
            model.addAttribute("errorMessage", "パスワードが一致しません");
            // 入力済みの内容を保持して登録画面に戻る
            model.addAttribute("user", user);
            return "register";
        }
    	// UserServiceに登録処理を依頼（パスワードの暗号化・DB保存）
        userService.register(user, password);
        
        // 登録完了後はログイン画面へリダイレクト
        return "redirect:/login";
    }
    
    // TOP画面の表示
    // ログイン成功後に遷移する画面
    @GetMapping("/top")
    public String topPage() {
        return "top";
    }
}
