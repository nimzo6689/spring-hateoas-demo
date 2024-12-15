package demo.hateoas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import demo.hateoas.domain.Article;

@Service
public class ArticleService {

    public List<Article> findByUserId(Long userId) {

        // デモ用のコードなので、固定値を返す。
        return List.of(
                new Article(1L, "Spring HATEOAS 入門",
                        "Spring Initializr のソースコードを読んでいると、..."),
                new Article(2L, "【Java環境構築】Windows+Scoop+VSCode で開発環境を構築する",
                        "ここ数年は Java の IDE として IntelliJ IDEA を使ってきましたが、..."));
    }

}
