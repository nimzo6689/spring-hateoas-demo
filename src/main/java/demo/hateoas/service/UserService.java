package demo.hateoas.service;

import org.springframework.stereotype.Service;

import demo.hateoas.domain.User;

@Service
public class UserService {

    public User findById(Long id) {

        // デモ用のコードなので、固定値を返す。
        return new User(1L, "ハテオアス太郎", "hateoas.taro@example.com");
    }
    
}
