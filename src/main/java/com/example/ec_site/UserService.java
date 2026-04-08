package com.example.ec_site;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// @Service: このクラスがサービス層であることをSpringに伝える
// UserDetailsService: Spring Securityのインターフェース
// → loadUserByUsernameメソッドの実装が必須になる
@Service
public class UserService implements UserDetailsService {

    // DBへの操作を担当するRepository
    private final UserRepository userRepository;

    // パスワードの暗号化を担当するEncoder
    // SecurityConfigで定義したBCryptPasswordEncoderが自動で注入される
    private final PasswordEncoder passwordEncoder;

    // コンストラクタインジェクション
    // SpringがUserRepositoryとPasswordEncoderを自動で渡してくれる
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Spring Securityがログイン時に自動で呼び出すメソッド
    // 引数のemailはログイン画面で入力したメールアドレス
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // メールアドレスでDBからユーザーを検索
        // 見つからない場合はUsernameNotFoundExceptionを投げる
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));

        // Spring SecurityのUserオブジェクトを作って返す
        // これをもとにパスワードの照合などが行われる
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            .password(user.getPasswords())
            .roles("USER")
            .build();
    }

    // 新規登録時に呼び出すメソッド
    // rawPassword: 画面で入力した生のパスワード
    public void register(User user, String rawPassword) {

        // パスワードを暗号化してUserにセット
        // 暗号化することでDBに生のパスワードが保存されない
        user.setPasswords(passwordEncoder.encode(rawPassword));

        // DBに保存
        userRepository.save(user);
    }
}